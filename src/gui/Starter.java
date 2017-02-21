package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

import algo.PrefixTreeAlgorithm;
import algo.PrimitivPS;
import algo.PrimitivSC;
import algo.PrimitivSS;
import algo.PrimitvPExec;
import algo.SortedParallelSearch;
import algo.SortedSearch;
import algo.StringSearch;

public class Starter extends JFrame {

	public static final StringSearch[] ALGORITHMS = new StringSearch[] { new PrimitivPS(), new PrimitvPExec(),
			new PrimitivSC(), new PrimitivSS(), new SortedSearch(), new SortedParallelSearch(), new PrefixTreeAlgorithm()  };

	public static void main(String[] args) {
		Arrays.sort(ALGORITHMS, new  Comparator<StringSearch>() {
			@Override
			public int compare(StringSearch o1, StringSearch o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		new Starter().setVisible(true);
	}
	

	private JRadioButton[] rb_algorithms;
	private List<String> data = new ArrayList<>();;
	private StringSearch activeAlgo;
	private JLabel footer = new JLabel("  ");
	private long precomputeTime = -1, calcTime = -1, itemCount = -1;
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private JTextField searchStringField = new JTextField(10);
	private JCheckBox incrementalCheckBox = new JCheckBox("incremental");

	public Starter() {
		super("PrefixSearch");
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton searchButton = new JButton("Search");
		JList<String> list = new JList<>(listModel);

		JPanel headerPanel = new JPanel();
		headerPanel.add(searchStringField);
		headerPanel.add(searchButton);
		headerPanel.add(incrementalCheckBox);
		this.add(headerPanel, BorderLayout.NORTH);
		this.add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		this.add(footer, BorderLayout.SOUTH);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem importFile = new JMenuItem("Import");
		JMenuItem exitMI = new JMenuItem("Exit");

		menu.add(importFile);
		menu.add(buildAlgoMenu());
		menu.add(exitMI);

		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		this.setSize(450, 400);
		this.setResizable(false);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				invokeAlgorithm();
			}
		});

		searchStringField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (incrementalCheckBox.isSelected()) {
					invokeAlgorithm();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (incrementalCheckBox.isSelected()) {
					invokeAlgorithm();
				}
			}
		});

		incrementalCheckBox.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				searchButton.setEnabled(!incrementalCheckBox.isSelected());
				if (incrementalCheckBox.isSelected()) {
					invokeAlgorithm();
				}
			}
		});

		importFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(".");
				jfc.setFileFilter(new FileFilter() {

					@Override
					public boolean accept(File f) {
						return f.isDirectory() || f.getName().endsWith(".txt");
					}

					@Override
					public String getDescription() {
						return ".txt";
					}
				});
				if (jfc.showOpenDialog(headerPanel) == JFileChooser.APPROVE_OPTION) {
					try {
						importData(jfc.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showConfirmDialog(headerPanel, e.getActionCommand(), "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});

		exitMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private JMenu buildAlgoMenu() {
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JRadioButton jRadioButton : rb_algorithms) {
					jRadioButton.setSelected(false);
				}
				JRadioButton rb = (JRadioButton) e.getSource();
				rb.setSelected(true);

				int idx = Integer.parseInt(rb.getName());
				activeAlgo = ALGORITHMS[idx];
				invokePrecompute();
				// TODO invoke precompute
			}
		};

		rb_algorithms = new JRadioButton[ALGORITHMS.length];
		JMenu algoMenu = new JMenu("Algorithms");
		for (int i = 0; i < ALGORITHMS.length; i++) {
			JRadioButton rb = new JRadioButton(ALGORITHMS[i].getName());
			rb.setName("" + i);
			rb.addActionListener(al);
			algoMenu.add(rb);
			rb_algorithms[i] = rb;

		}

		if (rb_algorithms.length > 0) {
			rb_algorithms[0].setSelected(true);
			activeAlgo = ALGORITHMS[0];
		}

		return algoMenu;
	}

	private void importData(File file) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(file));
		data.clear();
		data = br.lines().collect(Collectors.toList());
		br.close();
		invokePrecompute();

	}

	private void invokeAlgorithm() {
		listModel.clear();
		long currTime = System.currentTimeMillis();
		List<String> result = activeAlgo.search(searchStringField.getText());
		calcTime = System.currentTimeMillis() - currTime;
		itemCount = result.size();
		setLabelDesc();
		for (String string : result) {
			listModel.addElement(string);
		}
	}

	private void invokePrecompute() {
		long currTime = System.currentTimeMillis();
		activeAlgo.precompute(data);
		precomputeTime = System.currentTimeMillis() - currTime;
		calcTime = -1;
		setLabelDesc();
	}

	private void setLabelDesc() {
		String msg = "";
		if (precomputeTime >= 0) {
			msg += "PrecomputingTime=" + precomputeTime + "ms ";
		}
		if (calcTime >= 0) {
			msg += "SearchTime=" + calcTime + "ms ";
		}
		if (itemCount >= 0) {
			msg += "#Items=" + itemCount;
		}
		footer.setText(msg);
	}

}
