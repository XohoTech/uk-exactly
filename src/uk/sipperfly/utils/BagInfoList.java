package uk.sipperfly.utils;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import uk.sipperfly.ui.Exactly;

/**
 *
 * @author Rimsha Khalid(rimsha@avpreserve.com)
 */
public class BagInfoList extends JPanel {

	private List<BagInfoEntry> entries;
	private final Exactly parent;

	public BagInfoList(Exactly frame) {
		this.entries = new ArrayList<BagInfoEntry>();
		this.parent = frame;

	}

	/**
	 * Dynamically add Metadata field on Deliver tab
	 *
	 * @param label value for label input field
	 * @param value input text field
	 */
	public void addEntry(String label, String value) {
		int size = entries.size();
		BagInfoEntry initial = new BagInfoEntry(label, value, "", this, size);
		addItem(initial);
	}

	/**
	 * Add entries during runtime
	 *
	 * @param label
	 * @param value
	 * @param id    database id for label and value
	 */
	public void editEntry(String label, String value, String id) {
		this.parent.jPanel11.setVisible(true);
		int size = entries.size();
		BagInfoEntry theClone = new BagInfoEntry(label, value, id, this, size);
		addItem(theClone);
		this.parent.jPanel1.revalidate();
		this.parent.jPanel11.setVisible(false);
	}

	/**
	 * Add panel in list as well as in UI
	 *
	 * @param entry
	 */
	private void addItem(BagInfoEntry entry) {
		entries.add(entry);
		if (entry.getY() + 55 > this.parent.jPanel10.getHeight()) {
			int height = this.parent.jPanel10.getPreferredSize().height + 50;
			this.parent.jPanel10.setPreferredSize(new Dimension(571, height));
			int pheight = this.parent.jPanel11.getPreferredSize().height + 50;
			this.parent.jPanel11.setPreferredSize(new Dimension(571, pheight));
		}
		this.parent.jPanel10.add(entry);
		refresh();
	}

	/**
	 * Remove panel from UI
	 *
	 * @param entry
	 */
	public void removeItem(BagInfoEntry entry) {
		int index = entries.indexOf(entry) + 1;
		this.resetBounds(index);
		entries.remove(entry);
		this.parent.jPanel10.remove(entry);
		int height = this.parent.jPanel10.getPreferredSize().height - 50;
		this.parent.jPanel10.setPreferredSize(new Dimension(571, height));
		int pheight = this.parent.jPanel11.getPreferredSize().height - 50;
		this.parent.jPanel11.setPreferredSize(new Dimension(571, pheight));
		refresh();
	}

	/**
	 * Revalidate whole panel
	 */
	private void refresh() {
		if (entries.size() == 0) {
			this.parent.jPanel10.setPreferredSize(new Dimension(571, 10));
			this.parent.jPanel11.setPreferredSize(new Dimension(571, 118));
		}
		this.parent.jPanel10.revalidate();
		this.parent.jPanel11.revalidate();
		this.parent.jPanel1.revalidate();
		this.parent.jPanel11.setVisible(true);
	}

	/**
	 * Reset Bounds of panel
	 *
	 * @param index
	 */
	private void resetBounds(int index) {
		for (int i = index; i < entries.size(); i++) {
			int y = entries.get(i).getY();
			int newYAxis = y - 50;
			entries.get(i).setBounds(0, newYAxis, 571, 50);
		}
	}

	/**
	 *
	 * @return list of entries dynamically added to UI
	 */
	public List getList() {
		return entries;
	}

	/**
	 * Get value
	 *
	 * @param e object
	 * @return text of input field
	 */
	public String getJfieldTextValue(Object e) {
		BagInfoEntry entry = (BagInfoEntry) e;
		return entry.getValue();
	}

	/**
	 * Get label value
	 *
	 * @param e object
	 * @return text of input field
	 */
	public String getJfieldTextLabel(Object e) {
		BagInfoEntry entry = (BagInfoEntry) e;
		return entry.getLabel();
	}

	/**
	 * get the id
	 *
	 * @param e
	 * @return id if existed
	 */
	public String getJfieldTextId(Object e) {
		BagInfoEntry entry = (BagInfoEntry) e;
		return entry.getId();
	}

	/**
	 * Reset UI and bagInfoList
	 */
	public void resetEntryList() {
		for (BagInfoEntry e : entries) {
			this.parent.jPanel10.remove(e);
		}
		entries = new ArrayList<BagInfoEntry>();
		refresh();
	}

	/**
	 * Set db id for object
	 *
	 * @param e
	 * @param id
	 */
	public void setJFieldId(Object e, String id) {
		BagInfoEntry entry = (BagInfoEntry) e;
		entry.setId(id);
	}
}