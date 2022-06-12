package vocabulary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import vocabulary.Word;
import vocabulary.WordDB;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import javax.swing.Box;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
 
public class vocaGUI extends JFrame {
	ArrayList<Word> arr;
	WordDB dbw=new WordDB();
	private JFrame frame;
	private JPanel contentPane;
	private JTextField addEnglishText;
	private JTextField addKoreanText;
	static JList listEnglishList = new JList();
	static JList listKoreanList = new JList();
	static DefaultListModel listEnglishModel = new DefaultListModel();
	static DefaultListModel listKoreanModel = new DefaultListModel();
	private JTextField testTotalCountText;
	private JTextField testResultText;
	private JTextField testFailCountText;
	private JTable table;
	static ArrayList<Word> voc = new ArrayList<Word>();

	/**
	 * Launch the application.
	 */
	
	private void listSetting()
	{
		// 리스트 모델을 초기화합니다.
		listEnglishModel = new DefaultListModel();
		listKoreanModel = new DefaultListModel();
		// 현재 단어장 리스트에 있는 모든 정보를 리스트 모델에 담습니다.
		for(int i = 0; i < voc.size(); i++)
		{
			listEnglishModel.addElement((i + 1) + ". " + voc.get(i).getEnglish());
			listKoreanModel.addElement((i + 1) + ". " + voc.get(i).getKorean());
		}
		// 각각의 리스트가 리스트 모델을 포함하여 보여지게 됩니다.
		listEnglishList.setModel(listEnglishModel);
		listKoreanList.setModel(listKoreanModel);
		// 테스트 결과를 초기화합니다.
		testTotalCountText.setText(voc.size() + " 개");
		testResultText.setText("");
		testFailCountText.setText("");
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vocaGUI frame = new vocaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vocaGUI() {
		setTitle("영단어 암기 프로그램");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem1 = new JMenuItem("소개");
		mntmNewMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				switch(cmd) {
				case "소개" : 
					JOptionPane.showMessageDialog(null, "영단어 암기 프로그램 입니다.", "소개", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem1);
		mnNewMenu.addSeparator(); // 분리선
		JMenuItem mntmNewMenuItem2 = new JMenuItem("도움말");
		mntmNewMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				switch(cmd) {
				case "도움말" : 
					JOptionPane.showMessageDialog(null, "그런건 없다.", "도움말", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem2);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//단어장 인사말
		JLabel lblNewLabel = new JLabel("영단어 암기 프로그램입니다.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(102, 24, 470, 22);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("단어장에 단어를 추가하거나 테스트를 할 수 있습니다.");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(112, 43, 470, 33);
		contentPane.add(lblNewLabel_1);
		
		//단어 리스트
		JPanel listPanel = new JPanel();
		listPanel.setBounds(36, 86, 599, 465);
		listPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 3), "단어 리스트"));
		contentPane.add(listPanel);
		listPanel.setLayout(null);
		
		JLabel listEnglishLabel = new JLabel("영단어");
		listEnglishLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listEnglishLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listEnglishLabel.setBounds(12, 130, 287, 50);
		listPanel.add(listEnglishLabel);
		
		JLabel listKoreanLabel = new JLabel("해석");
		listKoreanLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listKoreanLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listKoreanLabel.setBounds(300, 130, 287, 50);
		listPanel.add(listKoreanLabel);
		
		JScrollPane listEnglishPane = new JScrollPane();
		listEnglishPane.setBounds(10, 181, 289, 274);
		listPanel.add(listEnglishPane);
		
		listEnglishList = new JList();
		listEnglishList.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listEnglishPane.setViewportView(listEnglishList);
		
		JScrollPane listKoreanPane = new JScrollPane();
		listKoreanPane.setBounds(300, 181, 287, 274);
		listPanel.add(listKoreanPane);
		
		listKoreanList = new JList();
		listKoreanList.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listKoreanPane.setViewportView(listKoreanList);
		
		//단어 추가
		JButton addButton = new JButton("추가");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				voc.add(new Word(addEnglishText.getText(), addKoreanText.getText()));
				listSetting();
				addEnglishText.setText("");
				addKoreanText.setText("");
				//insert(String addEnglishText, String addKoreanText);
			}
		});
		addButton.setBounds(398, 30, 76, 71);
		addButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		addButton.setBackground(Color.lightGray);
		listPanel.add(addButton);
		
		//영단어 입력
		JLabel addEnglishLabel = new JLabel("영단어 입력");
		addEnglishLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		addEnglishLabel.setBounds(56, 36, 106, 18);
		listPanel.add(addEnglishLabel);
		
		addEnglishText = new JTextField();
		addEnglishText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		addEnglishText.setBounds(174, 33, 174, 24);
		listPanel.add(addEnglishText);
		addEnglishText.setColumns(10);
		
		//해석 입력
		JLabel addKoreanLabel = new JLabel("해석 입력");
		addKoreanLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		addKoreanLabel.setBounds(56, 80, 106, 18);
		listPanel.add(addKoreanLabel);
		
		addKoreanText = new JTextField();
		addKoreanText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		addKoreanText.setColumns(10);
		addKoreanText.setBounds(174, 77, 174, 24);
		listPanel.add(addKoreanText);
		
		
		
		//단어 삭제
		JButton deleteButton = new JButton("삭제");
		deleteButton.setBounds(486, 30, 76, 71);
		deleteButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		deleteButton.setBackground(Color.lightGray);
		listPanel.add(deleteButton);
		
		//테스트
		JPanel testPanel = new JPanel();
		testPanel.setBounds(36, 573, 599, 239);
		testPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 3), "단어 테스트"));
		contentPane.add(testPanel);
		testPanel.setLayout(null);
		
		JButton testButton = new JButton("테스트 시작");
		testButton.addActionListener(new ActionListener() {
			// 테스트 진행
			public void actionPerformed(ActionEvent e) {
				String english;
				ArrayList<Word> testWords = new ArrayList<Word>();
				int failCount = 0;
				// 단어 리스트 숨김
				listEnglishModel.clear();
				listKoreanModel.clear();
				for(int i = 0; i < voc.size(); i++) {
					english = JOptionPane.showInputDialog((i + 1) + "번째 단어 : '" + voc.get(i).getKorean() + "' 현재까지 총 틀린 횟수 : " + failCount);
					if(english == null || !english.equalsIgnoreCase(voc.get(i).getEnglish())) {
						testWords.add(new Word(english, voc.get(i).getKorean()));
						failCount++;
					}
					else {
						testWords.add(new Word(english, voc.get(i).getKorean()));
					}
				}
				// 단어 리스트 보여줌
				listSetting();
				// 테스트 결과 출력
				DecimalFormat form = new DecimalFormat("#.#");
				if(voc.size() != 0)
				{
					testResultText.setText(form.format(100 * (((double)voc.size() - (double)failCount) / (double)voc.size())) + " 점");
					testFailCountText.setText(failCount + " 개");
				}
				// 결과 메시지
				String resultMessage = "";
				resultMessage += "테스트 점수 : " + form.format(100 * (((double)voc.size() - (double)failCount) / (double)voc.size())) + " 점";
				resultMessage += "\n틀린 개수 : " + failCount + " 개";
				JTextArea textArea = new JTextArea(resultMessage);
				JScrollPane scrollPane = new JScrollPane(textArea);  
				textArea.setLineWrap(true);  
				textArea.setWrapStyleWord(true); 
				scrollPane.setPreferredSize(new Dimension(250, 250));
				JOptionPane.showMessageDialog(null, scrollPane, "테스트 결과", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//테스트 실행 버튼
		testButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		testButton.setBounds(409, 31, 139, 167);
		testButton.setBackground(Color.lightGray);
		testPanel.add(testButton);
		
		//테스트 내용 안내
		JLabel testTotalCountLabel = new JLabel("총 단어 수");
		testTotalCountLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testTotalCountLabel.setBounds(52, 37, 119, 33);
		testPanel.add(testTotalCountLabel);
		
		testTotalCountText = new JTextField();
		testTotalCountText.setHorizontalAlignment(SwingConstants.RIGHT);
		testTotalCountText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testTotalCountText.setEditable(false);
		testTotalCountText.setBounds(172, 36, 163, 36);
		testPanel.add(testTotalCountText);
		testTotalCountText.setColumns(10);
		
		JLabel testResultLabel = new JLabel("최근 점수");
		testResultLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testResultLabel.setBounds(52, 98, 119, 33);
		testPanel.add(testResultLabel);
		
		testResultText = new JTextField();
		testResultText.setHorizontalAlignment(SwingConstants.RIGHT);
		testResultText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testResultText.setEditable(false);
		testResultText.setColumns(10);
		testResultText.setBounds(172, 97, 163, 36);
		testPanel.add(testResultText);
		
		JLabel testFailCountLabel = new JLabel("최근 틀린 개수");
		testFailCountLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testFailCountLabel.setBounds(52, 165, 119, 33);
		testPanel.add(testFailCountLabel);
		
		testFailCountText = new JTextField();
		testFailCountText.setHorizontalAlignment(SwingConstants.RIGHT);
		testFailCountText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testFailCountText.setEditable(false);
		testFailCountText.setColumns(10);
		testFailCountText.setBounds(172, 161, 163, 36);
		testPanel.add(testFailCountText);
		
		setSize(700, 900);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
