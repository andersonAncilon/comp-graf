package minhaprimeiraapp.com.example.splatter.compgraf;

/**
 * Created by splatter on 18/10/2017.
 */

public class Paralelogramo extends Geometria {

    //Construtor da Classe
    public Paralelogramo(int largura, int altura, float ie, float se, float id, float sd)
    {
        float[] vetorVertices = {
                -largura / ie, - altura / 2, //Canto inferior esquerdo
                -largura / se, altura / 2, //Canto superior esquerdo
                largura / id, -altura / 2, //Canto inferior direito
                largura / sd, altura / 2,}; //Canto superior direito

        setVetorVertices(vetorVertices);
    }
}
