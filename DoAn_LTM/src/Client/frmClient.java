/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

//ham khoi tao cac thu vien can dung
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.net.*;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author hongs
 */
public class frmClient extends javax.swing.JFrame {
    
    //khoi tao cac bien can dung
    private DatagramSocket socket;
    private InetAddress ipserver ;//luu dia chi may server
    private int port = 1234;//su dung port 1234 de giao tiep voi server
    private String key = ""; //khoi tao key de luu tru khoa

    /**
     * Creates new form frmClient
     */
    public frmClient() {
        initComponents();
    }
    //ham lay ip cua ten mien
    private InetAddress IP(){
        try {
            //lay ten domain ran vao InetAddress
            String domain = this.txt_DiaChi.getText();
            ipserver = InetAddress.getByName(domain);
            
        } catch (UnknownHostException e) {
        }
        //tra ve dia chi ip cua domain
        if (ipserver == null ) {
            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại địa chỉ !");
            return null;
        }
        return ipserver;
    }
    
    //ham ma hoa voi thuat toan Caeser
    char mahoakt(char c,int k){
        if(!Character.isLetter(c)) return c;
        return (char) ((((Character.toUpperCase(c) - 'A') + k) % 26 + 26) % 26 + 'A');
    }
    private String mahoa(String br,int k){
        String kq="";
        int n=br.length();
        for(int i=0;i<n;i++)
            kq+=mahoakt(br.charAt(i),k);
        return kq;
    }
    //ham kiem tra nguoi dung co nhap vao so nguyen
    private boolean Khoa(){
        //khoi tao bien boolean
        boolean isCheck = true;
        //kiem tra khoa phai so nguyen khong
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(txt_Khoa.getText().trim());
        //kiem tra khoa co ton tại ky tu
        String paString = "[a-zA-Z]";
        Pattern p1 = Pattern.compile(paString);
        Matcher m1 = p1.matcher(txt_Khoa.getText().trim());
        //khoi tao bien dia chi
        String diachi = this.txt_DiaChi.getText();
        //kiem tra khoa co trong hay khong
        if (txt_Khoa.getText().trim().isEmpty()) {
             JOptionPane.showMessageDialog(this, "Vui lòng không để trống khóa !");
              isCheck = false;
        } else {
            //kiem tra khoa co ton tai ky tu
            if (m1.find()== true){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập khóa không có ký tự !");
                isCheck = false;
            } else {
                //kiem tra nguoi dung nhap vao co phai so khong
                if (m.find() == false) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập khóa là số nguyên !");
                    isCheck = false;
                } else {
                    //kiem tra van ban co trong hay khong
                    if (txt_VanBan.getText().trim().isEmpty()) {
                         JOptionPane.showMessageDialog(this, "Vui lòng không để trống văn bản !");
                          isCheck = false;
                    } else{
                        if (diachi.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Vui lòng không để trống tên địa chỉ!");
                            isCheck = false;
                        } else if (!diachi.contains(".")) {
                            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại địa chỉ !");
                            isCheck = false;
                        } 
                    }
                }
            }
        }
        
        return isCheck;
    }
    //ham kiem tra textfiled ky tu co de trong khong 
    private boolean Check(){ 
        boolean isCheck = true;
        String kytu = this.txt_Kytu.getText().trim();
        if (kytu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống ký tự !");
            isCheck = false;
        } 
        return isCheck;
    }
    
    //ham ma hoa van ban truoc khi gui len Server
    private String Mahoa(){
        //chuyen doi khoa kieu string sang int
        int k=Integer.valueOf(key.trim());
        String br=this.txt_VanBan.getText();
        //tra ve chuoi sau khi ma hoa
  
        return mahoa(br,k);
    }
    
    //ham gui du lieu len server
    private void SendData(String chuoi){ 
        byte []sendData;
        InetAddress ip = IP();
        try {
            socket = new DatagramSocket();
            sendData = chuoi.getBytes();//chuyen du lieu thanh dang byte roi truyen di
            //Data gram packet dung de luu du lieu
            DatagramPacket seDatagramPacket = new DatagramPacket(sendData, sendData.length, ip, port);
            socket.send(seDatagramPacket);//gui du lieu di
               
        } catch (IOException e) {
        }
    }
    
    //ham nhan du lieu tu Server
    private DatagramPacket ReceviceData() throws IOException{ 
        //Nhan chuoi ket qua tu server
        byte[] buffer = new byte[65507];//do dai toi da cua goi tin
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);//nhan chuoi tra ve
        socket.close();
               
        return receivePacket;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        File = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        txt_Khoa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_Thoat = new javax.swing.JButton();
        btn_DoiKhoa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_VanBan = new javax.swing.JTextArea();
        btn_Gui = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_DiaChi = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        listView = new javax.swing.JList<>();
        btn_MoFile = new javax.swing.JButton();
        btn_GhiFile = new javax.swing.JButton();
        txt_Kytu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_Dem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Lập trình mạng với giao thức UDP");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));
        getContentPane().add(txt_Khoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 450, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jLabel3.setText("Kết Quả");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 60, -1));

        jLabel4.setText("Nhập khóa");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel5.setText("Nhập văn bản");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        btn_Thoat.setText("Thoát");
        btn_Thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThoatActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Thoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, -1, -1));

        btn_DoiKhoa.setText("Trao đổi khóa");
        btn_DoiKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DoiKhoaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_DoiKhoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, -1, -1));

        txt_VanBan.setColumns(20);
        txt_VanBan.setRows(5);
        jScrollPane2.setViewportView(txt_VanBan);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 450, 170));

        btn_Gui.setText("Gửi đi");
        btn_Gui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuiActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Gui, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 440, -1, -1));

        jLabel6.setText("Nhập địa chỉ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));
        getContentPane().add(txt_DiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 450, -1));

        jScrollPane3.setViewportView(listView);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, 450, 170));

        btn_MoFile.setText("Mở File");
        btn_MoFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MoFileActionPerformed(evt);
            }
        });
        getContentPane().add(btn_MoFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, -1, -1));

        btn_GhiFile.setText("Ghi File");
        btn_GhiFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GhiFileActionPerformed(evt);
            }
        });
        getContentPane().add(btn_GhiFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, -1, -1));
        getContentPane().add(txt_Kytu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 120, -1));

        jLabel7.setText("Nhập ký tự");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        btn_Dem.setText("Đếm");
        btn_Dem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DemActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Dem, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThoatActionPerformed
        // Thoat chuong trinh
        System.exit(0);
    }//GEN-LAST:event_btn_ThoatActionPerformed

    private void btn_DoiKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DoiKhoaActionPerformed
        //keim tra xem nguoi dung nhap vao phai so khong
        if (Khoa() == true) {
            //gui chuoi len server kem theo co
            String chuoi = "1"+"@"+this.txt_Khoa.getText().trim();
            SendData(chuoi);//gui du lieu len server
            
            //nhan du lieu tu server
            DatagramPacket packet;
            try {
                packet = ReceviceData(); //nhan khoa tu Client truyen len
                chuoi = new String(packet.getData()).trim();//lay du lieu cua may Client
                
                if (chuoi != null) {     
                    System.out.println("Khóa sau khi trao đổi là: " + chuoi);
                    key = chuoi; //ran khoa moi cho bien key
                } else {
                    JOptionPane.showMessageDialog(this, "Server không phản hồi !!!");
                } 
            } catch (IOException ex) {
                Logger.getLogger(frmClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_DoiKhoaActionPerformed

    private void btn_GuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuiActionPerformed
        //kieu tra xem du lieu tra ve co rong khong
        try {
            if (Mahoa() != null) {
                DefaultListModel dmModel = new DefaultListModel();//khoi tao danh sach
                String data ="2"+"@"+key+"@"+ Mahoa();//gui du lieu len server gom co, khoa, noi dung 
                SendData(data);//gui ban ma len server

                //nhan du lieu gui ve tu serevr
                DatagramPacket packet = ReceviceData(); //nhan khoa tu Client truyen len
                String chuoi = new String(packet.getData()).trim();//lay du lieu cua may Client
                System.out.println("Chuỗi nhân về từ Server: " + chuoi);
                String[] s_array = chuoi.split("@");//chuyen chuoi string thanh mang kieu string
                //chạy vòng lặp tách số lần điếm của mỗi ký tự ra khỏi mảng
                for (int i = 0; i < s_array.length; i++) {

                    String kytu =  s_array[i];//rán từng item trong danh sach cho chuoi kytu
                    System.out.println("Mỗi item trong mảng: " + kytu); // xuất mỗi item ra man hình
                    String[] item = kytu.split("-");//chuyen item kieu string sang mang kieu string
                    String thongtin = ""; //luu thong tin cua tung item
                    for (int j = 0; j < item.length; j++) {
                        //kiem tra xem item trong mang phai ky ty khong
                        Pattern p = Pattern.compile("[A-Z]");
                        Matcher m = p.matcher(item[j]);
                        //kiem tra xem item trong mang phai so khong
                        Pattern p1 = Pattern.compile("[0-9]");
                        Matcher m1 = p1.matcher(item[j]);
                        if (m.find()) {
                            thongtin = "Số lần xuất hiện của ký tự " + item[j];
                        } else if (m1.find()) {
                            thongtin = thongtin + " là :" +item[j];
                        }
                    }
                    dmModel.addElement(thongtin);//them du lieu vao danh sach
                }
                this.listView.setModel(dmModel);//hien thi danh sach len giao dien
                System.out.println("Van ban sau khi giai ma: " + chuoi);
                }
        } catch (IOException e) {
        }
    }//GEN-LAST:event_btn_GuiActionPerformed

    private void btn_MoFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MoFileActionPerformed
        //thiet lap hien thi hop thoai chon duong dan
        this.File.setVisible(true);
        //chon hop thoai hien thi len
        if(this.File.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            //neu nut open duoc chon
            try {
                BufferedReader br = null;
                //lay ten file
                String filename = File.getSelectedFile().getName();
                // lay duong dan file
                String dir = File.getCurrentDirectory().toString();
                //lay duong dan den tap tin
                String fileName = dir+"\\"+filename;

                br = new BufferedReader(new FileReader(fileName));
                StringBuffer sb = new StringBuffer();
                JOptionPane.showMessageDialog(null,"Đã mở file thành công !!!");

                char[] ca =new char[5];
                while(br.ready()){
                    int len =br.read(ca);
                    sb.append(ca, 0, len);
                }
                br.close();

                System.out.println("Du lieu la :"+""+sb);
                String chuoi = sb.toString();

                txt_VanBan.setText(chuoi);//hien thi van ban len giao dien 
            } catch (HeadlessException | IOException e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }//GEN-LAST:event_btn_MoFileActionPerformed

    private void btn_GhiFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GhiFileActionPerformed
        //thiet lap hien thi hop thoai chon duong dan
        this.File.setVisible(true);
        //chon hop thoai hien thi len
        if(this.File.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){   
            try{
                //lay ten file
                String filename = File.getSelectedFile().getName();
                // lay duong dan file
                String dir = File.getCurrentDirectory().toString();
                //lay duong dan den tap tin
                String fileName = dir+"\\"+filename;
                //khoi tao doi tuong BufferedWriter
                BufferedWriter bw = null;
                String s;
                //tao vong lap lay tung item trong danh sach
                
                s = listView.getModel().toString();
                bw = new BufferedWriter(new FileWriter(fileName));
                bw.write(s);
                bw.close();
                JOptionPane.showMessageDialog(null,"Ghi File thành công !!!");

                }
                catch(IOException ex){
                    //Logger.getLogger(Ceasar.class.getName()).log(Level.SEVERE,null,ex);
                }
        }
    }//GEN-LAST:event_btn_GhiFileActionPerformed

    private void btn_DemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DemActionPerformed
        String kytu = this.txt_Kytu.getText().trim();
        //kieu tra xem du lieu tra ve co rong khong
        try {
            if (Mahoa() != null) {
                DefaultListModel dmModel = new DefaultListModel();//khoi tao danh sach
                String data ="3"+"@"+key+"@"+kytu+"@"+ Mahoa();//gui du lieu len server gom co, khoa, noi dung 
                SendData(data);//gui ban ma len server

                //nhan du lieu gui ve tu serevr
                DatagramPacket packet = ReceviceData(); //nhan khoa tu Client truyen len
                String chuoi = new String(packet.getData()).trim();//lay du lieu cua may Client
                System.out.println("Chuỗi nhân về từ Server: " + chuoi);
                String thongtin = "Số lần xuất hiện của "+kytu+" là: "+chuoi;
                
                dmModel.addElement(thongtin);//them du lieu vao danh sach
                
                this.listView.setModel(dmModel);//hien thi danh sach len giao dien
                System.out.println("Van ban sau khi giai ma: " + chuoi);
                }
        } catch (IOException e) {
        }
    }//GEN-LAST:event_btn_DemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser File;
    private javax.swing.JButton btn_Dem;
    private javax.swing.JButton btn_DoiKhoa;
    private javax.swing.JButton btn_GhiFile;
    private javax.swing.JButton btn_Gui;
    private javax.swing.JButton btn_MoFile;
    private javax.swing.JButton btn_Thoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listView;
    private javax.swing.JTextField txt_DiaChi;
    private javax.swing.JTextField txt_Khoa;
    private javax.swing.JTextField txt_Kytu;
    private javax.swing.JTextArea txt_VanBan;
    // End of variables declaration//GEN-END:variables
}
