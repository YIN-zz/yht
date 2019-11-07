package com.ygt.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class ClientCharMain extends JFrame implements ActionListener,KeyListener {
    public static void main(String[] args) {
        //调用构造方法
        new ClientCharMain();
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
    public ClientCharMain(){
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
        this.setTitle("QQ聊天页面客户端");
        this.setSize(500,500);
        this.setLocation(800,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        /******TCP  客户端   start******/
        //给发送按钮绑定一个监听点击事件
        jb.addActionListener(this);
        //给文本框绑定一个键盘点击事件
        jtf.addKeyListener(this);
        try{
            //1.创建一个客户器端的套接字（尝试连接）
            Socket socket = new Socket("192.168.3.10", 8888);
            //2.获取 socket  通道的输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //3.获取 scoket  通道的输出流
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //循环读取数据，并拼接到文本域中
            String line = null;
            while((line = br.readLine())!=null){
                jta.append(line+System.lineSeparator());
            }
            //4.关闭 socket  通道
            socket.close();
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

    private void sendDataToSocket(){
        //1.获取文本框中需要发送的数据内筒
        String text = jtf.getText();
        //2.拼接内容
        text = "客服端对服务端说"+text;
        //3.自己显示
        jta.append(text+System.lineSeparator());
        //4.发送
        try {
            bw.write(text);
            bw.newLine();
            bw.flush();
            //5.清空
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
