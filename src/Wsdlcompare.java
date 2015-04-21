import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import com.predic8.wsdl.*;
import com.predic8.wsdl.diff.WsdlDiffGenerator;
import com.predic8.soamodel.Difference;

public class Wsdlcompare {

	public static void main(String[] args) {

		try {
			Definitions wsdl1 = new WSDLParser().parse(new FileInputStream(
					new File("Def1.xml")));
			Definitions wsdl2 = new WSDLParser().parse(new FileInputStream(
					new File("Def2.xml")));

			WsdlDiffGenerator diffGen = new WsdlDiffGenerator(wsdl1, wsdl2);
			List<Difference> lst = diffGen.compare();
			for (Difference diff : lst) {
				dumpDiff(diff, "");
			}
		} catch (Exception e) {

		}
	}

	private static void dumpDiff(Difference diff, String level) {
		System.out.println(level + diff.getDescription());
		for (Difference localDiff : diff.getDiffs()) {
			dumpDiff(localDiff, level + "  ");
		}
	}
}