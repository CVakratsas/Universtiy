import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class VisualizeNetwork extends JFrame{
	
	private Registry registry;
	private JFrame frame;
	private JTextField diameterField;
	private Border border;
	
	public VisualizeNetwork(Registry registry) {
		
		this.registry = registry;
		
		border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
		
		diameterField = new JTextField();
		diameterField.setEditable(false);
		diameterField.setBorder(border);
		diameterField.setBackground(Color.WHITE);
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(360,340);
		frame.setTitle("Suspects Network");
		
		Graph g = new UndirectedSparseGraph();
		
		for(Suspect suspect: registry.getListOfSuspects()) {
			g.addVertex(suspect.getCodeName());
		}
		
		for(Suspect suspect: registry.getListOfSuspects()) {
			for(Suspect sus: suspect.getPartners()) {
				// Edge name example "RustyKnifeXFrozenBear"
				g.addEdge(suspect.getCodeName() + "X" + sus.getCodeName(), suspect.getCodeName(), sus.getCodeName());
			}
		}
		
		// Diameter
		double diameter = DistanceStatistics.diameter(g);
		diameterField.setText(String.format("Diameter = %.1f", diameter));
		
		VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(g), new Dimension(250, 250));
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        

        frame.getContentPane().add(vs);
        frame.add(diameterField, BorderLayout.PAGE_END);
        frame.setVisible(true);
	}
	
}
