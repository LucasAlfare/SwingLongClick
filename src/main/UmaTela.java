package main;

import javax.swing.*;
import java.awt.*;

/**
 * somente para testes visuais, sobretudo
 */
public class UmaTela extends JFrame{

    private static final Color COR_PRIMARIA = Color.BLACK;

    private JButton jButton;
    private JLabel jLabel;

    public UmaTela(){
        setSize(400, 200);
        setDefaultCloseOperation(3);
        setLayout(new FlowLayout());

        jLabel = new JLabel("STATUS");
        jButton = new JButton("CLIQUE ME!");
        jButton.setFocusable(false);

        //adiciono aqui o lister personalizado para o frame global
        this.addKeyListener(new UmKeyListener(COR_PRIMARIA, jLabel, jButton));

        add(jLabel);
        add(jButton);
    }
}
