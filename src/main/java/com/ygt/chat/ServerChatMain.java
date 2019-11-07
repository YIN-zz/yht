package com.ygt.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

//页面显示，继承JFRame
//1.定义组件
//2.在初始化方法中构造窗体组件
//3.使用网络编程完成数据的传输（TCP、UDP协议）（这里用到是TCP）
//4.实现"发送"按钮的监听点击事件
//5.shixian
public class ServerChatMain extends JFrame implements ActionListener, KeyListener {
    public static void main(String[] args) {
        //调用构造方法
        new ServerChatMain();
    }
    //属性
    //文本域
    private JTextArea jta;
    //滚动条
    private JScrollPane jsp;
    //面板
    private JPanel jp;
    //文本框
    private JTextField jtf;
    //按钮
    private JButton jb;
    //输出流
    private BufferedWriter bw = null;

    //构造方法
    public ServerChatMain(){
        //初始化组件
        jta = new JTextArea();
        //设置文本域内不可以进行编辑
        jta.setEditable(false);
        //将文本域添加到滚动条中，实现滚动效果
        jsp = new JScrollPane(jta);
        //面板
        jp = new JPanel();
        //文本框的长度
        jtf = new JTextField(30);
        //按钮内容
        jb = new JButton("发送");
        //将文本框与按钮添加到面板中
        jp.add(jtf);
        jp.add(jb);
        //将滚动条与面板全部添加到窗体中
        this.add(jsp, BorderLayout.CENTER);
        this.add(jp,BorderLayout.SOUTH);
        //设置标题，大小，位置，是否可见
        this.setTitle("QQ聊天页面服务端");
        this.setSize(500,500);
        this.setLocation(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        /******TCP  服务端   start******/
        //给发送按钮绑定监听点击事件
        jb.addActionListener( this);
        //给文本框绑定一个键盘点击事件
        jtf.addKeyListener(this);
        try{
            //1.创建一个服务器端的套接字
            ServerSocket serverSocket = new ServerSocket(8888);
            //2.等待客户端的连接
            Socket socket = serverSocket.accept();
            //3.获取 socket  通道的输入流(输出流实现读取数据 一行一行的读)BufferedReader->readLine()
            //InputStream inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //4.获取 scoket  通道的输出流(输出流实现写出数据，也是写一行，换一行，刷新）BufferedWriter ->newLine();
            //当用户点击发送按钮的时候写出数据
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //循环读取数据，并拼接到文本域中
            String line = null;
            while((line = br.readLine())!=null){
                jta.append(line+System.lineSeparator());
            }
            //5.关闭 socket  通道
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /******TCP  服务端   end******/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sendDataToSocket();
    }

    //行为
    @Override
    public void keyPressed(KeyEvent e) {
        //回车键
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            //发送数据到socket通道中
            sendDataToSocket();
        }
    }

    //定义一个方法，实现将数据发送到socket通道中
    private void sendDataToSocket(){
        //1.获取文本框中的发送内容
        String text = jtf.getText();
        //2.拼接需要发送的数据内容
        text = "服务端对客户端说"+text;
        //3.自己也需要显示
        jta.append(text+System.lineSeparator());
        //4.发送
        try {
            bw.write(text);
            bw.newLine();
            bw.flush();
            //5.清空文本框内容
            jtf.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
