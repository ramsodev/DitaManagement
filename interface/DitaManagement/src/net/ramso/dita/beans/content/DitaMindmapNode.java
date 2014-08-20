/**
 *
 */
package net.ramso.dita.beans.content;

import java.util.List;
import java.util.Map;

import net.ramso.dita.beans.config.ConfigData;
import net.ramso.dita.beans.config.ConfigDataTools;
import net.ramso.dita.utils.GenericData;

import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

/**
 * @author ramso
 *
 */
public class DitaMindmapNode extends DefaultMindmapNode implements MindmapNode {

	/**
	 *
	 */
	private static final long serialVersionUID = -5720609120230788527L;
	private List<ConfigData> attributes;

	public DitaMindmapNode() {
		super();
	}

	public DitaMindmapNode(String label) {
		super(label);
	}

	public DitaMindmapNode(String label, Object data) {
		super(label, data);
	}

	public DitaMindmapNode(String label, Object data, String fill) {
		super(label, data, fill);
	}

	public DitaMindmapNode(String label, Object data, String fill,
			boolean selectable) {
		super(label, data, fill, selectable);
	}

	public List<ConfigData> getAttributes() {
		if (attributes == null) {
			final Map<String, Object> atts = ((GenericData) getData())
					.getAllAttributes();
			attributes = ConfigDataTools.getFromAttributes(atts.entrySet());
		}
		return attributes;
	}

}
