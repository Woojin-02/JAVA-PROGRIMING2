package vocabulary;

import java.awt.*;
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
//import vocabulary.WordDB;

import javax.swing.*;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import java.util.HashMap;
import java.awt.GridLayout;
 
public class vocaGUI extends JFrame {
	ArrayList<Word> arr;
	//WordDB dao=new WordDB();
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
		//JFrame 설정
		setTitle("영단어 암기 프로그램");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//메뉴바 생성
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//메뉴바-도움
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		//메뉴바-도움-소개
		JMenuItem MenuItem1 = new JMenuItem("소개");
		MenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				switch(cmd) {
				case "소개" : 
					JOptionPane.showMessageDialog(null, "영단어 암기 프로그램 입니다. "
							+ "단어장에 단어를 추가하거나 테스트를 할 수 있습니다.", "소개", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		});
		helpMenu.add(MenuItem1);
		helpMenu.addSeparator();
		//메뉴바-도움-도움말
		JMenuItem MenuItem2 = new JMenuItem("도움말");
		MenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				switch(cmd) {
				case "도움말" : 
					JOptionPane.showMessageDialog(null, "그런건 없다.", "도움말", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		});
		helpMenu.add(MenuItem2);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//단어장 인사말
		JLabel lblNewLabel = new JLabel("영단어 암기 프로그램입니다.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(101, 56, 470, 22);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		
		//단어 리스트
		JPanel listPanel = new JPanel();
		listPanel.setBounds(36, 88, 599, 491);
		listPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 3), "단어 리스트"));
		contentPane.add(listPanel);
		listPanel.setLayout(null);
		
		//영단어 리스트
		JLabel listEnglishLabel = new JLabel("영단어");
		listEnglishLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listEnglishLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listEnglishLabel.setBounds(12, 126, 287, 50);
		listPanel.add(listEnglishLabel);
		
		JScrollPane listEnglishPane = new JScrollPane();
		listEnglishPane.setBounds(10, 168, 289, 316);
		listPanel.add(listEnglishPane);
		
		listEnglishList = new JList();
		listEnglishList.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listEnglishPane.setViewportView(listEnglishList);
		
		//해석 리스트
		JLabel listKoreanLabel = new JLabel("해석");
		listKoreanLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listKoreanLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listKoreanLabel.setBounds(300, 126, 287, 50);
		listPanel.add(listKoreanLabel);
		
		JScrollPane listKoreanPane = new JScrollPane();
		listKoreanPane.setBounds(300, 168, 287, 316);
		listPanel.add(listKoreanPane);
		
		listKoreanList = new JList();
		listKoreanList.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listKoreanPane.setViewportView(listKoreanList);
		
		
		//툴바 생성
		JToolBar toolBar = new JToolBar("Voca Menu");
		toolBar.setSize(684, 36);
		toolBar.setLocation(0, 0);
		toolBar.setBackground(Color.lightGray);
		toolBar.setFloatable(false);
		
		//툴바-단어장 생성
		JButton newbtn = new JButton("New");
		newbtn.setToolTipText("단어장을 생성합니다.");
		newbtn.setFont(new Font("나눔고딕", Font.BOLD, 13));
		toolBar.add(newbtn);
		toolBar.addSeparator();
		
		JTextField addvVocaField = new JTextField("");
		addvVocaField.setHorizontalAlignment(SwingConstants.LEFT);
		addvVocaField.setToolTipText("생성할 단어장 이름을 입력하세요.");
		addvVocaField.setFont(new Font("나눔고딕", Font.BOLD, 13));
		toolBar.add(addvVocaField);
		toolBar.addSeparator();
		
		//툴바-단어장 선택
		JComboBox<String> combo = new JComboBox<String>();
		combo.setEditable(true);
		combo.setToolTipText("단어장을 선택하거나 직접 입력하세요.");
		combo.addItem("Java");
		toolBar.add(combo);
		toolBar.addSeparator();
		
		//툴바-리스트 새로고침
		JButton setbtn = new JButton("Set");
		setbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listSetting();
			}
		});
		setbtn.setToolTipText("단어 리스트를 새로고침합니다.");
		setbtn.setFont(new Font("나눔고딕", Font.BOLD, 13));
		toolBar.add(setbtn);
		
		contentPane.add(toolBar);
		
		//영단어 입력
		JLabel addEnglishLabel = new JLabel("영단어 입력");
		addEnglishLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		addEnglishLabel.setBounds(49, 36, 106, 18);
		listPanel.add(addEnglishLabel);
		
		addEnglishText = new JTextField();
		addEnglishText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		addEnglishText.setBounds(167, 33, 174, 24);
		listPanel.add(addEnglishText);
		addEnglishText.setColumns(10);
		
		//해석 입력
		JLabel addKoreanLabel = new JLabel("해석 입력");
		addKoreanLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		addKoreanLabel.setBounds(49, 84, 106, 18);
		listPanel.add(addKoreanLabel);
		
		addKoreanText = new JTextField();
		addKoreanText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		addKoreanText.setColumns(10);
		addKoreanText.setBounds(167, 81, 174, 24);
		listPanel.add(addKoreanText);
		
		//단어 추가
		JButton addButton = new JButton("추가");
		addButton.setToolTipText("단어를 추가합니다.");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wordInsert(addEnglishText.getText(), addKoreanText.getText());
				listSetting();
				addEnglishText.setText("");
				addKoreanText.setText("");
			}
		});
		addButton.setBounds(372, 26, 76, 38);
		addButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		addButton.setBackground(Color.lightGray);
		listPanel.add(addButton);
				
		
		//단어 삭제
		JButton deleteButton = new JButton("삭제");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wordDelete(addEnglishText.getText());
				listSetting();
				addEnglishText.setText("");
				addKoreanText.setText("");
			}
		});
		deleteButton.setToolTipText("입력한 단어를 삭제합니다.");
		deleteButton.setBounds(372, 74, 76, 38);
		deleteButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		deleteButton.setBackground(Color.lightGray);
		listPanel.add(deleteButton);
		
		//단어 검색
		JButton selectButton = new JButton("검색");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wordSearch(addEnglishText.getText());
				addKoreanText.setText("");
			}
		});
		selectButton.setToolTipText("검색하고자 하는 영단어 혹은 해석을 입력하세요.");
		selectButton.setBounds(472, 26, 76, 38);
		selectButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		selectButton.setBackground(Color.lightGray);
		listPanel.add(selectButton);
		
		//단어 수정
		JButton updateButton = new JButton("수정");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wordUpdate(addEnglishText.getText(), addKoreanText.getText());
				listSetting();
				addEnglishText.setText("");
				addKoreanText.setText("");
			}
		});
		updateButton.setToolTipText("수정하고자 하는 영단어와 해석을 입력하세요.");
		updateButton.setBounds(472, 74, 76, 36);
		updateButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		updateButton.setBackground(Color.lightGray);
		listPanel.add(updateButton);
		
		//영어 리스트 보이기/가리기
		JButton engClearBtn = new JButton("");
		engClearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listEnglishModel.clear();
			}
		});
		engClearBtn.setBounds(279, 145, 18, 18);
		engClearBtn.setBackground(Color.lightGray);
		listPanel.add(engClearBtn);
		
		//해석 리스트 보이기/가리기
		JButton korClearBtn = new JButton("");
		korClearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listKoreanModel.clear();
			}
		});
		korClearBtn.setBounds(569, 145, 18, 18);
		korClearBtn.setBackground(Color.lightGray);
		listPanel.add(korClearBtn);
		
		//테스트
		JPanel testPanel = new JPanel();
		testPanel.setBounds(36, 600, 599, 216);
		testPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 3), "단어 테스트"));
		contentPane.add(testPanel);
		testPanel.setLayout(null);
		
		JButton testButton = new JButton("테스트 시작");
		testButton.setToolTipText("테스트를 시작합니다.");
		testButton.addActionListener(new ActionListener() {
			//테스트 진행
			public void actionPerformed(ActionEvent e) {
				//이전 테스트 결과 초기화
				testResultText.setText("");
				testFailCountText.setText("");
				list();		//단어 불러오기
				String korean;
				ArrayList<Word> testWords = new ArrayList<Word>();
				int failCount = 0;
				//단어 리스트 숨김
				listEnglishModel.clear();
				listKoreanModel.clear();
				for(int i = 0; i < voc.size(); i++) {
					korean = JOptionPane.showInputDialog((i + 1) + "번째 단어 : '" + voc.get(i).getEnglish() + "' 현재까지 총 틀린 횟수 : " + failCount);
					if(korean == null || !korean.equalsIgnoreCase(voc.get(i).getKorean())) {
						testWords.add(new Word(korean, voc.get(i).getEnglish()));
						failCount++;
					}
					else {
						testWords.add(new Word(korean, voc.get(i).getEnglish()));
					}
				}
				//단어 리스트 보여줌
				listSetting();
				//테스트 결과 출력
				DecimalFormat form = new DecimalFormat("#.#");
				if(voc.size() != 0)
				{
					testResultText.setText(form.format(100 * (((double)voc.size() - (double)failCount) / (double)voc.size())) + " 점");
					testFailCountText.setText(failCount + " 개");
				}
				//결과 메시지
				String resultMessage = "";
				resultMessage += "테스트 점수 : " + form.format(100 * (((double)voc.size() - (double)failCount) / (double)voc.size())) + " 점";
				resultMessage += "\n틀린 개수 : " + failCount + " 개";
				JTextArea textArea = new JTextArea(resultMessage);
				JScrollPane scrollPane = new JScrollPane(textArea);  
				textArea.setLineWrap(true);  
				textArea.setWrapStyleWord(true); 
				scrollPane.setPreferredSize(new Dimension(100, 100));
				JOptionPane.showMessageDialog(null, scrollPane, "테스트 결과", JOptionPane.INFORMATION_MESSAGE);
			}
		});	
		//테스트 실행 버튼
		testButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		testButton.setBounds(402, 33, 133, 147);
		testButton.setBackground(Color.lightGray);
		testPanel.add(testButton);
		
		//테스트 내용 안내
		//총 단어 수
		JLabel testTotalCountLabel = new JLabel("총 단어 수");
		testTotalCountLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testTotalCountLabel.setBounds(52, 39, 119, 33);
		testPanel.add(testTotalCountLabel);
		
		testTotalCountText = new JTextField();
		testTotalCountText.setHorizontalAlignment(SwingConstants.RIGHT);
		testTotalCountText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testTotalCountText.setEditable(false);
		testTotalCountText.setBounds(172, 38, 163, 36);
		testPanel.add(testTotalCountText);
		testTotalCountText.setColumns(10);
		
		//최근 점수
		JLabel testResultLabel = new JLabel("최근 점수");
		testResultLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testResultLabel.setBounds(52, 90, 119, 33);
		testPanel.add(testResultLabel);
		
		testResultText = new JTextField();
		testResultText.setHorizontalAlignment(SwingConstants.RIGHT);
		testResultText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testResultText.setEditable(false);
		testResultText.setColumns(10);
		testResultText.setBounds(172, 89, 163, 36);
		testPanel.add(testResultText);
		
		//최근 틀린 개수
		JLabel testFailCountLabel = new JLabel("최근 틀린 개수");
		testFailCountLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testFailCountLabel.setBounds(52, 143, 119, 33);
		testPanel.add(testFailCountLabel);
		
		testFailCountText = new JTextField();
		testFailCountText.setHorizontalAlignment(SwingConstants.RIGHT);
		testFailCountText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		testFailCountText.setEditable(false);
		testFailCountText.setColumns(10);
		testFailCountText.setBounds(172, 142, 163, 36);
		testPanel.add(testFailCountText);
		
		setSize(700, 900);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	//DB 주소
	String url = "jdbc:mysql://localhost:3306/voca";
	String user = "root";
	String pwd = "1234";
	
	//DB 연결
	public void WordDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	//DB에 단어 입력
	public void wordInsert(String english, String korean) {
		Connection con = null;
		PreparedStatement ps = null;
			try {
				con = DriverManager.getConnection(url, user, pwd);
				String sql = "INSERT INTO word VALUES (?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, english);
				ps.setString(2,korean);
				ps.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "이미 단어장에 존재하는 단어입니다.", "Message", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				try {
					if(con != null) con.close();
					if(ps != null) ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
	
	//DB에서 단어 삭제
	public void wordDelete(String english) {
		Connection con = null;
		PreparedStatement ps=null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "DELETE FROM word WHERE eng = ?";
			ps=con.prepareStatement(sql);
			ps.setString(1, english);
			ps.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "삭제 실패", "Message", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
				if(ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	//DB에서 단어 검색
		public ArrayList<Word> wordSearch(String english) {
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			ArrayList<Word>arr=new ArrayList<Word>();
			int i=0;
			try {
				con=DriverManager.getConnection(url,user,pwd);
				String sql="SELECT * FROM word WHERE eng like ?";
				ps=con.prepareStatement(sql);
				ps.setString(1, english);
				rs=ps.executeQuery();
				while(rs.next()) {
					Word w=new Word();
					w.setEnglish(rs.getString("eng"));
					w.setKorean(rs.getString("kor"));
					arr.add(w);
				}
				//검색 내용 출력
				listEnglishModel = new DefaultListModel();
				listKoreanModel = new DefaultListModel();
				listEnglishModel.addElement((i + 1) + ". " + arr.get(i).getEnglish());
				listKoreanModel.addElement((i + 1) + ". " + arr.get(i).getKorean());
				listEnglishList.setModel(listEnglishModel);
				listKoreanList.setModel(listKoreanModel);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "검색 실패", "Message", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				try {
					if(con != null) con.close();
					if(ps != null) ps.close();
					if(rs != null) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return arr;
		}
	
	//DB에서 단어 수정
	public void wordUpdate(String english, String korean) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			String sql="UPDATE word SET kor = ? WHERE eng = ?";
			con=DriverManager.getConnection(url,user,pwd);
		ps=con.prepareStatement(sql);
			ps.setString(1, korean);
			ps.setString(2, english);
			ps.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "수정 실패", "Message", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
				if(ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Word> listSetting() {
		//DB에서 내용 불러오기
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Word> voc = new ArrayList<Word>();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "SELECT * FROM word";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Word w = new Word();
				w.setEnglish(rs.getString("eng"));
				w.setKorean(rs.getString("kor"));
				voc.add(w);
			}
			//단어장 리스트에 내용 출력
			listEnglishModel = new DefaultListModel();
			listKoreanModel = new DefaultListModel();
			for(int i = 0; i < voc.size(); i++)
			{
				listEnglishModel.addElement((i + 1) + ". " + voc.get(i).getEnglish());
				listKoreanModel.addElement((i + 1) + ". " + voc.get(i).getKorean());
			}
			listEnglishList.setModel(listEnglishModel);
			listKoreanList.setModel(listKoreanModel);
			//총 단어 수 표시
			testTotalCountText.setText(voc.size() + " 개");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "리스트를 불러오지 못했습니다.", "Message", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return voc;
	}
	
	public void list() {
		//DB에서 내용 불러오기
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "SELECT * FROM word";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Word w = new Word();
				w.setEnglish(rs.getString("eng"));
				w.setKorean(rs.getString("kor"));
				voc.add(w);
			}
			//단어장 리스트에 내용 출력
			listEnglishModel = new DefaultListModel();
			listKoreanModel = new DefaultListModel();
			for(int i = 0; i < voc.size(); i++)
			{
				listEnglishModel.addElement((i + 1) + ". " + voc.get(i).getEnglish());
				listKoreanModel.addElement((i + 1) + ". " + voc.get(i).getKorean());
			}
			listEnglishList.setModel(listEnglishModel);
			listKoreanList.setModel(listKoreanModel);
			//총 단어 수 표시
			testTotalCountText.setText(voc.size() + " 개");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "리스트를 불러오지 못했습니다.", "Message", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
}
