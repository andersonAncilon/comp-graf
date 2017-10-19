package minhaprimeiraapp.com.example.splatter.compgraf;

import android.app.Activity;
import android.icu.text.AlphabeticIndex;
import android.opengl.GLSurfaceView;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

//define a classe que vai controlar o comportamento do desenho (o que sera desenhado)
class Renderizador implements GLSurfaceView.Renderer
{
    Long tempoInicial = null;
    int fps;
    Triangulo vrTriangulo = null;
    Triangulo vrTriangulo2 = null;
    Triangulo vrTriangulo3 = null;
    Triangulo vrTriangulo4 = null;
    Triangulo vrTriangulo5 = null;
    Triangulo vrTriangulo6 = null;
    Triangulo vrTriangulo7 = null;

    Paralelogramo vrParelelogramo = null;





    @Override
    //chamado quando a superficie de desenho for criada (1vez)
    public void onSurfaceCreated(GL10 vrOpengl, EGLConfig eglConfig)
    {
        //CONFIGURANDO A LIMPEZA DE FUNDO (RGBT)
        vrOpengl.glClearColor(1.0F, 1.0F, 1.0F, 0.0F);


        //TARTARUGA
        vrTriangulo = new Triangulo(700,400);//Carapaça, parte 1
        vrTriangulo.setXY(525,625);
        vrTriangulo.incAngulo(-180);


        //Triangulo 2
        vrTriangulo2 = new Triangulo(700,400); //Carapaça, parte 2
        vrTriangulo2.setXY(525,1025);
        vrTriangulo2.incAngulo(0);


        //Triangulo 3
        vrTriangulo3 = new Triangulo(425,200); // Pata superior esquerda
        vrTriangulo3.setXY(175,1100);
        vrTriangulo3.incAngulo(25);

        //paralelogramo
        vrParelelogramo = new Paralelogramo( 200, 200, 1, 3.7f, 3.7f, 1); //Pata superior direita
        vrParelelogramo.setXY(850,1075);
        vrParelelogramo.incAngulo(-35);


        //Triangulo 4
        vrTriangulo4 = new Triangulo(350,150); //Pata inferior esquerda
        vrTriangulo4.setXY(210,490);
        vrTriangulo4.incAngulo(65);

        //Triangulo 5
        vrTriangulo5 = new Triangulo(350,150); //Pata inferior direita
        vrTriangulo5.setXY(835,490);
        vrTriangulo5.incAngulo(-65);


        //Triangulo 6
        vrTriangulo6 = new Triangulo( 275, 175 ); //Cabeça, parte 1
        vrTriangulo6.setXY(525,1480);
        vrTriangulo6.incAngulo(0);


        //Triangulo 7
        vrTriangulo7 = new Triangulo( 275, 175 ); //Cabeça, parte 2
        vrTriangulo7.setXY(525,1310);
        vrTriangulo7.incAngulo(180);




    }

    @Override
    public void onSurfaceChanged(GL10 vrOpengl, int width, int heigth)
    {
        //chamado quando as configurações da superficie desenho forem alteradas
        //ex: rotação do aparelho
        Log.i("INFO", "SUPERFICIE ALTERADA" + width+ " " + heigth );


        //INICIAR A PREPARAÇÃO DO OPENGL PARA DESENHO
        //CONFIGURAR O VIEWPORT - AREA DE DESENHO UTILIZADA DA TELA
        vrOpengl.glViewport(0,0,width, heigth);
        //FAZ A OPENGL APONTAR PARA A MATRIZ DE PROJEÇÃO
        vrOpengl.glMatrixMode(GL10.GL_PROJECTION);
        //CARREGA A MATRIZ DE IDENTIDADE NA PROJEÇÃO
        vrOpengl.glLoadIdentity();

        //CHAMA O COMANDO PARA CONFIGURAR A AREA DE DESENHO (VOLUME DE REDENRIZAÇÃO)
        vrOpengl.glOrthof(0,width,0,heigth,-1,1);

        //SOLICITA AO OPENGL APONTAR PARA A MATRIZ DE MODELOS DE CAMERA
        vrOpengl.glMatrixMode(GL10.GL_MODELVIEW);
        vrOpengl.glLoadIdentity();

        //FloatBuffer vertices = criaFloatBuffer(vetorJava);
        //SOLICITA  A BIBLIOTECA QUE HABILITE  O RECURSO DE VETOR DE VERTICES
        vrOpengl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //REGISTRAO VETOR DE VERTICES NA MAQUINA OPENGL PARA USO FUTURO
        vrOpengl.glVertexPointer(2, GL10.GL_FLOAT,0, vrTriangulo.getVetorVertices());


    }

    @Override
    public void onDrawFrame(GL10 vrOpengl)
    {



        //SOLICITA A OPENGL QUE LIMPE A MATRIZ DE PIXEL DE CORES
        vrOpengl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        vrTriangulo.setCor(0.8f,0,0.6f);
        vrTriangulo2.setCor(1,0.4f,0.2f);
        vrTriangulo3.setCor(0.2f,1,0);
        vrTriangulo4.setCor(1,0,0);
        vrTriangulo5.setCor(1,0.6f,0);
        vrParelelogramo.setCor(0.2f,0,1);
        vrTriangulo6.setCor(0.5f,0,0.2f);


        //Desenha tangram

        vrTriangulo.desenha(vrOpengl); //Carapaça pt1
        vrTriangulo2.desenha(vrOpengl); //Capraça pt2
        vrTriangulo3.desenha(vrOpengl); //Pata superior esquerda
        vrParelelogramo.desenha(vrOpengl); //Pata superior direita
        vrTriangulo4.desenha(vrOpengl); //Pata inferior esquerda
        vrTriangulo5.desenha(vrOpengl); //Pata inferior direta
        vrTriangulo6.desenha(vrOpengl); //Cabeça pt1
        vrTriangulo7.desenha(vrOpengl);  //Cabeça pt2






    }
}

public class TelaPrincipal extends Activity
{
    //cria a referencia para o objeto responsavel pela exibição do desenho
    GLSurfaceView vrSuperficieDesenho = null;
    Renderizador vrRenderizador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //SOLICITA A RETIRADA DA BARRA DE TITULO
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //validar variavel de referencia para a superficie do desenho
        vrSuperficieDesenho = new GLSurfaceView(this);
        vrRenderizador = new Renderizador();
        vrSuperficieDesenho.setRenderer(vrRenderizador);

        //CONFIGURA MODO FULLSCREEN NA LARGURA E ALTURA
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        //SOLICITA QUE O APLICATIVO FIQU7E ATIVO NA TELA MESMO POR INATIVIDADE DO USUARIO
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(vrSuperficieDesenho);
    }
}
