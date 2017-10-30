package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class UmKeyListener implements KeyListener {

    //somente para testes visuais
    private Color corPrimaria;
    //somente para testes visuais
    private JComponent[] components;

    //cronometro que vai marcar quanto tempo que a tecla está sendo pressionada
    private Timer timer;
    //variavel pra indicar que o tempo de clique longo foi alcançado
    private boolean isLong;

    //somente para testes visuais
    public UmKeyListener(Color corPrimaria, JComponent... components){
        this.corPrimaria = corPrimaria;
        this.components = components;
    }

    //método que manipula as teclas quando o dedo vai pra baixo (actionDown)
    @Override
    public void keyPressed(KeyEvent e) {
        //verifica se a tecla pressionada no telado é a tecla desejada, nesse exemplo
        //usei a barra de espaços (32)
        if (e.getKeyCode() == KeyEvent.VK_SPACE){

            //verifica se o cronometro ainda não foi inicializado
            if (timer == null){

                //caso não tenha sido inicializado, inicializa-se
                timer = new Timer();

                /*
                 * adiciona-se uma tarefa numa taxa de tempo fixa.
                 *
                 * nesse caso o método recebe 3 parametros: uma tarefa,
                 * um tempo de atraso inicial e o tempo fixo pra repetir
                 * a tarefa.
                 *
                 * para caracterizar um clique longo, eu defini que se este
                 * timer puder ficar ativo por pelo menos 400 milissegundos
                 * (tempo de atraso até começar a realizar a tarefa), significa
                 * que houve o tal clique longo.
                 *
                 * a tarefa deste cronometro seria basicamente alterar a variavel
                 * de controle para um valor positivo (true), marcando-a, assim,
                 * como indicadora do clique longo.
                 *
                 * então, para que a tarefa seja executada, é necessário que o
                 * usuário fique com a tecla pressionada por pelo menos 400 milissegundos.
                 * Caso isso não aconteça, vai acontecer o que está definido lá no outro
                 * método.......
                 *
                 * (obs.: o 1000 ali não influencia em nada, neste caso)
                 */
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        isLong = true;
                    }
                }, 400, 1000);
            }

            //somente para testes visuais
            for (JComponent component : components) {
                component.setForeground(isLong ? Color.GREEN : Color.YELLOW);
            }
        }
    }

    //método que manipula os cliques quando o dedo vai pra cima (actionUp)
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){

            //somente para testes visuais
            for (JComponent component : components){
                component.setForeground(corPrimaria);
            }

            /*
             * sempre que este método for chamado, independente se houve clique longo
             * ou não, a função dele será alterar o valor da variável de controle do
             * clique longo para um valor negativo. Assim pode-se saber quando o usuário
             * não está mais apertando a tecla X com o seu dedo!
             */
            isLong = false;

            //cancela-se o timer e torna-o nulo, novamente
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
