/*=
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;

import static hashing.Adddet.t1;
import static hashing.Adddet.t2;
import static hashing.Adddet.t3;
import static hashing.Adddet.t4;
import static hashing.Adddet.t5;
import static hashing.Adddet.t6;
import static hashing.Search.s1;
import static hashing.Search.s2;
import static hashing.Search.s3;
import static hashing.Search.s4;
import static hashing.Search.s5;
import static hashing.Search.s6;
import static hashing.Search.s7;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import static hashing.Delete.a1;
import static hashing.Delete.a2;
import static hashing.Delete.a3;
import static hashing.Delete.a4;
import static hashing.Delete.a5;
import static hashing.Delete.a6;
import static hashing.Delete.a7;
import static hashing.Delete.a8;
import static hashing.Hashing.hashfu;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Mithun
 */


class Hashing {
    static Scanner sc = new Scanner(System.in);
    static final int bucket_size = 10;
    static final int record_size = 100;
    static final long[] h = new long[20];  
    static int i = 0;


    public static class node {

        char[] UID = new char[10];
        String UName = new String(new char[40]);
        String PURPOSE = new String(new char[40]);
        String SYSTEMID = new String(new char[40]);
        String CHECK_IN = new String(new char[70]);
        String CHECK_OUT = new String(new char[40]);

        public node link;
        

    }
    static node head[] = new node[10];

    static int hashfu(char[] uid) {
        int x;
        int sum = uid[0] + uid[1] + uid[2];
        x = (sum % bucket_size) * record_size;
        return x;


    }

    static void pack() throws IOException {
        node q[] = new node[10];
        BufferedWriter file = null;

        try {
            FileWriter fstream = new FileWriter("M:\\Hashing\\sam.txt");
            file = new BufferedWriter(fstream);
            int z = 0;
            while (h[z] != 0)
                z++;
            for (int k = 0; k <= z; k++) {
                q[k] = head[k];
                while (q[k] != null) {
                    file.write(q[k].UID);
                    file.write("|");
                    file.write(q[k].UName);
                    file.write("|");
                    file.write(q[k].PURPOSE);
                    file.write("|");
                    file.write(q[k].SYSTEMID);
                    file.write("|");
                    file.write(q[k].CHECK_IN);
                    file.write("|");
                    file.write(q[k].CHECK_OUT);
                    file.write("|");

                    file.write("-->");

                    q[k] = q[k].link;
                }
                file.write("\n");
            }

            file.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }



    }

    public static void write() throws IOException {
        node p = new node();
        node q = new node();

        int count;
        

        //System.out.println("Enter the MID:");
        //p.MID = sc.next().toCharArray();
        p.UID = t1.getText().toCharArray();


        //System.out.println("Enter the Name:");
        //p.Name = sc.next();
        p.UName = t2.getText();
        //System.out.println("Enter the Age");
        //p.Age = sc.next();
        p.PURPOSE = t3.getText();
        //System.out.println("Enter the Category");
        //p.Category = sc.next();
        p.SYSTEMID = t4.getText();
        //System.out.println("Enter the Location");
        //p.Location = sc.next();
        p.CHECK_IN = t5.getText();
        //System.out.println("Enter Experience");
        //p.Experience = sc.next();
        p.CHECK_OUT = t6.getText();

        p.link = null;

        int bucket_no;
        bucket_no = hashfu(p.UID);

        if (bucket_no == 0)
            bucket_no++;

        while (h[i] != 0)
            i++;

        for (int j = 0; j <= i; j++) {
            if (h[j] == bucket_no)
                i = j;
        }

        if (i >= bucket_size) {
            System.out.println("EXCEEDING BUCKET SIZE");
            return;
        }
        h[i] = bucket_no;
        for (int k = 0; k <= i; k++) {

            if (k == i) {
                q = head[k];
                count = 0;
                while ((q != null) && (count < 5)) {
                    count++;
                    q = q.link;

                }
                if (count == 5) {
                    System.out.println("OVERFLOW");
                    i++;

                    return;
                }

                if (head[k] == null) {
                    head[k] = p;
                    head[k].link = null;

                } else {
                    q = head[k];
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = p;
                    //return;
                }
            }

        }
        i++;
        pack();
    }

    static void search() {
        try {
            char[] mid = new char[11];
            //System.out.println("Enter the MID to be searched!");
            //mid = sc.next().toCharArray();
            mid = s4.getText().toCharArray();


            int bucket_no;
            bucket_no = hashfu(mid);
            if (bucket_no == 0) {
                bucket_no++;
            }
            int row = -1, k = 0;
            //System.out.println(h[k]);
            while (h[k] != 0) {
                k++;
            }

            for (int j = 0; j < k; j++) {
                if (h[j] == bucket_no) {
                    row = j;
                    break;
                }
            }

            node q = new node();
            node r = new node();
            


            if (row != -1) {


                q = head[row];
               
                r = q.link;


                if ((Arrays.equals(mid, q.UID)) && (q != null)) {

                    //System.out.println("\nthe information about this usn is\n");
                    //System.out.print(q.MID); 
                    String str = new String(q.UID); //System.out.println(q.MID);
                    s1.setText(str);
                    s2.setText(q.UName);
                    //System.out.print("\t"+q.Name);
                    s3.setText(q.PURPOSE);
                    //System.out.print("\t"+q.Age);
                    s5.setText(q.SYSTEMID);
                    s6.setText(q.CHECK_IN);
                    //System.out.print("\t"+q.Category);
                    s7.setText(q.CHECK_OUT);
                    //System.out.print("\t"+q.Location);
                    //System.out.print("\t"+q.Experience);
                    
                    JOptionPane.showMessageDialog(null, "SEARCH SUCCESSFUL");
                } 
                
                else if ((Arrays.equals(mid, r.UID)) && (r != null)) {


                    //System.out.println("\nthe information about this usn is\n");
                    //System.out.print(r.MID); 
                    String str = new String(r.UID);
                    s1.setText(str);
                    s2.setText(r.UName);
                    //System.out.print("\t"+r.Name);
                    s3.setText(r.PURPOSE);
                    //System.out.print("\t"+r.Age);
                    s5.setText(r.SYSTEMID);
                    s6.setText(r.CHECK_IN);
                    //System.out.print("\t"+r.Category);
                    s7.setText(r.CHECK_OUT);
                    //System.out.print("\t"+r.Location);
                    //System.out.print("\t"+r.Experience);
                    JOptionPane.showMessageDialog(null, "SEARCH SUCCESSFUL");

                } else if ((Arrays.equals(mid, r.link.UID)) && (r.link != null)) {

                    //System.out.println("\nthe information about this usn is\n");
                    //System.out.print(r.MID); 
                    String str = new String(r.link.UID); //System.out.println(q.MID);
                    s1.setText(str);
                    s2.setText(r.link.UName);
                    //System.out.print("\t" + r.link.Name);
                    s3.setText(r.link.PURPOSE);
                    //System.out.print("\t" + r.link.Age);
                    s5.setText(r.link.SYSTEMID);
                    s6.setText(r.link.CHECK_IN);
                    //System.out.print("\t" + r.link.Category);
                    s7.setText(r.link.CHECK_OUT);
                    //System.out.print("\t" + r.link.Location);
                    //System.out.print("\t" + r.link.Experience);
                    JOptionPane.showMessageDialog(null, "SEARCH SUCCESSFUL");
                }
            } 
            else 
            {
                s1.setText("");
                s2.setText("");
                s3.setText("");
                s5.setText("");
                s6.setText("");
                s7.setText("");
                JOptionPane.showMessageDialog(null, "SEARCH UNSUCCESSFUL");
            }
        } catch (Exception e) {
            //System.out.println(e);
                s1.setText("");
                s2.setText("");
                s3.setText("");
                s5.setText("");
                s6.setText("");
                s7.setText("");
            JOptionPane.showMessageDialog(null, "SEARCH UNSUCESSFUL");
        }
    }

    static void search1() {
        try {
            char[] mid = new char[11];
            //System.out.println("Enter the MID to be searched!");
            //mid=sc.next().toCharArray();
            mid = a4.getText().toCharArray();
            //System.out.println(usn);

            int bucket_no;
            bucket_no = hashfu(mid);
            if (bucket_no == 0) {
                bucket_no++;
            }
            int row = -1, k = 0;
            //System.out.println(h[k]);
            while (h[k] != 0) {
                k++;
            }

            for (int j = 0; j < k; j++) {
                if (h[j] == bucket_no) {
                    row = j;
                    break;
                }
            }

            node q = new node();
            node r = new node();

            if (row != -1) {


                q = head[row];
                r = q.link;

                if ((Arrays.equals(mid, q.UID)) && (q != null)) {

                    //System.out.println("\nthe information about this usn is\n");
                    //System.out.print(q.MID); 
                    String str = new String(q.UID); //System.out.println(q.MID);
                    a1.setText(str);
                    a2.setText(q.UName);
                    //System.out.print("\t"+q.Name);
                    a3.setText(q.PURPOSE);
                    //System.out.print("\t"+q.Age);
                    a5.setText(q.SYSTEMID);
                    a6.setText(q.CHECK_IN);
                    //System.out.print("\t"+q.Category);
                    a7.setText(q.CHECK_OUT);
                    //System.out.print("\t"+q.Location);
                    //System.out.print("\t"+q.Experience);
                    JOptionPane.showMessageDialog(null, "SEARCH SUCCESSFUL");

                } else if ((Arrays.equals(mid, r.UID)) && (r != null)) {


                    //System.out.println("\nthe information about this usn is\n");
                    //System.out.print(r.MID); 
                    String str = new String(r.UID);
                    a1.setText(str);
                    a2.setText(r.UName);
                    //System.out.print("\t"+r.UName);
                    a3.setText(r.PURPOSE);
                    //System.out.print("\t"+r.Age);
                    a5.setText(r.SYSTEMID);
                    a6.setText(r.CHECK_IN);
                    //System.out.print("\t"+r.Category);
                    a7.setText(r.CHECK_OUT);
                    //System.out.print("\t"+r.Location);
                    //System.out.print("\t"+r.Experience);
                    JOptionPane.showMessageDialog(null, "SEARCH SUCCESSFUL");
                } else if ((Arrays.equals(mid, r.link.UID)) && (r.link != null)) {
                    //System.out.println("\nthe information about this usn is\n");
                    //System.out.print(r.MID); 
                    String str = new String(r.link.UID); //System.out.println(q.MID);
                    a1.setText(str);
                    a2.setText(r.link.UName);
                    //System.out.print("\t"+r.link.UName);
                    a3.setText(r.link.PURPOSE);
                    //System.out.print("\t"+r.link.Age);
                    a5.setText(r.link.SYSTEMID);
                    a6.setText(r.link.CHECK_IN);
                    //System.out.print("\t"+r.link.Category);
                    a7.setText(r.link.CHECK_OUT);
                    //System.out.print("\t"+r.link.Location);
                    //System.out.print("\t"+r.link.Experience);
                    JOptionPane.showMessageDialog(null, "SEARCH SUCCESSFUL");
                }



                //q=q.link;

            } 
            else {
               
                a1.setText("");
                a2.setText("");
                a3.setText("");
                a5.setText("");
                a5.setText("");
                a7.setText("");
                JOptionPane.showMessageDialog(null, "SEARCH UNSUCCESSFUL CANT DELETE");
            }
        } 
        catch (Exception e) 
        {
            s1.setText("");
            s2.setText("");
            s3.setText("");
            s5.setText("");
            s5.setText("");
            s7.setText("");
            JOptionPane.showMessageDialog(null, "SEARCH UNSUCCESSFUL CANT DELETE");
        }
    }
    
    
     static void delete_rec() throws IOException
    {
       
            char[] uid = new char[11];
            System.out.println("Enter the UID to be !");
            //mid=sc.next().toCharArray();
            uid = a8.getText().toCharArray();
            //System.out.println(usn);

            int bucket_no;
            bucket_no = hashfu(uid);
            if (bucket_no == 0) {
                bucket_no++;
            }
            int row = -1, k = 0;
            //System.out.println(h[k]);
            while (h[k] != 0) {
                k++;
            }

            for (int j = 0; j < k; j++) {
                if (h[j] == bucket_no) {
                    row = j;
                    break;
                }
            }
        
        node temp=new node();
        node prev=new node();
        
        
        temp= head[row];
        prev = null;
 
        // If head node itself holds the key to be deleted
        if (temp != null && Arrays.equals(uid,temp.UID))
        {
            
            head[row] = temp.link;
           a1.setText("");
           a2.setText("");
            //System.out.print("\t"+q.UName);
           a3.setText("");
            //System.out.print("\t"+q.Age);
           a5.setText("");
           a6.setText("");
            //System.out.print("\t"+q.Category);
           a7.setText("");
            //System.out.print("\t"+q.Location);
            //System.out.print("\t"+q.Experience);
           JOptionPane.showMessageDialog(null, "DELETE SUCCESSFUL");
            return ;
        }
 
        
        while (temp != null && !(Arrays.equals(uid,temp.UID)))
        {
             
        prev = temp;
        temp = temp.link;
         a1.setText("");
         a2.setText("");
         //System.out.print("\t"+q.UName);
         a3.setText("");
            //System.out.print("\t"+q.Age);
         a5.setText("");
         a6.setText("");
            //System.out.print("\t"+q.Category);
         a7.setText("");
            //System.out.print("\t"+q.Location);
            //System.out.print("\t"+q.Experience);
         JOptionPane.showMessageDialog(null, "DELETE SUCCESSFUL");
            
        }    
 
        // If key was not present in linked list
        if (temp == null) 
        {
        a1.setText("");
        a2.setText("");
        a3.setText("");
        a5.setText("");
        a5.setText("");
        a7.setText("");
        JOptionPane.showMessageDialog(null,"DELETE UNSUCCESSFUL");
        return;
        }
         prev.link = temp.link;
        
    }
 
        
    

        


    /*static void delete_rec() throws IOException {
            try {
                
                char[] mid = new char[11];
                //System.out.println("Enter the MID to be searched!");
                //mid=sc.next().toCharArray();
                mid = a4.getText().toCharArray();
                //System.out.println(usn);

                int bucket_no;
                bucket_no = hashfu(mid);
                if (bucket_no == 0) {
                    bucket_no++;
                }
                int row = -1, k = 0;
                //System.out.println(h[k]);
                while (h[k] != 0) {
                    k++;
                }

                for (int j = 0; j < k; j++) {
                    if (h[j] == bucket_no) {
                        row = j;
                        break;
                    }
                }

                node q = new node();
                node r = new node();

                if (row != -1) {

                        
                    q = head[row];
                    r = q.link;

                    if ((Arrays.equals(mid, q.MID)) && (q != null)) {

                        a1.setText("");
                        a2.setText("");
                        //System.out.print("\t"+q.UName);
                        a3.setText("");
                        //System.out.print("\t"+q.Age);
                        a5.setText("");
                        a6.setText("");
                        //System.out.print("\t"+q.Category);
                        a7.setText("");
                        //System.out.print("\t"+q.Location);
                        //System.out.print("\t"+q.Experience);
                        JOptionPane.showMessageDialog(null, "DELETE SUCCESSFUL");
                        
                        
                        
                    } else if ((Arrays.equals(mid, r.MID)) && (r != null)) {
                        //System.out.println("\nthe information about this usn is\n");
                        //System.out.print(r.MID); 
                        String str = new String(r.MID);
                        a1.setText("");
                        a2.setText("");
                        //System.out.print("\t"+r.UName);
                        a3.setText("");
                        //System.out.print("\t"+r.Age);
                        a5.setText("");
                        a6.setText("");
                        //System.out.print("\t"+r.Category);
                        a7.setText("");
                        //System.out.print("\t"+r.Location);
                        //System.out.print("\t"+r.Experience);
                        JOptionPane.showMessageDialog(null, "DELETE SUCCESSFUL");
                    } else if ((Arrays.equals(mid, r.link.MID)) && (r.link != null)) {
                        //System.out.println("\nthe information about this usn is\n");
                        //System.out.print(r.MID); 
                        String str = new String(r.link.MID); //System.out.println(q.MID);
                        a1.setText("");
                        a2.setText("");
                        //System.out.print("\t"+r.link.UName);
                        a3.setText("");
                        //System.out.print("\t"+r.link.Age);
                        a5.setText("");
                        a6.setText("");
                        //System.out.print("\t"+r.link.Category);
                        a7.setText("");
                        //System.out.print("\t"+r.link.Location);
                        //System.out.print("\t"+r.link.Experience);
                        JOptionPane.showMessageDialog(null, "DELETE SUCCESSFUL");
                    } else {

                        JOptionPane.showMessageDialog(null, "DELETE UNSUCCESSFUL");
                    }
                }
            }
                

            
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"DELETE UNSUCCESSFUL");
            }
    }*/

                void display() throws IOException {
                    try
                    {
                     node q[]=new node[10];
                    int z=0;
                    while(h[z]!=0)
                    z++;
                    
                    for(int k=0;k<=z;k++)
                    {
                        q[k]=head[k];
                        while(q[k]!=null)
                        {
                           System.out.print(q[k].UID);
                           System.out.print("\t"+q[k].UName);
                           System.out.print("\t"+q[k].PURPOSE);
                           System.out.print("\t"+q[k].SYSTEMID);
                           System.out.print("\t"+q[k].CHECK_IN);
                           System.out.print("\t"+q[k].CHECK_OUT);
                           System.out.print("--->");
                           q[k]=q[k].link;
                        }
                        System.out.println("\n");
                    
                        
                    }
                   
                    }
                    catch(Exception e)
                    {
                        System.out.println("NO DATA TO DISPLAY");
                    }
                }
                
      
  
                   


                public static void main(String[] args) throws IOException {
                    int choice;
                    Hashing h1 = new Hashing();
                    for (;;) {
                        System.out.println("\n1-add a record\t2-display\t3-search\t4-exit");
                          choice = sc.nextInt();
                            
                            switch (choice) {
                            case 1:
                                h1.write();
                                break;
                            case 2:
                                h1.display();
                                break;
                            case 3:
                                h1.delete_rec();
                                pack();
                                break;

                            case 4:
                                System.exit(1);
                                break;

                            default:
                                System.out.println("\n invalid choice");

                        }

                    }
                }
            }