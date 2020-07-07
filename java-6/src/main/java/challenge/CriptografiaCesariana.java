package challenge;

public class CriptografiaCesariana implements Criptografia {
    public String criptografado = new String();
    private String descriptografado = new String();
    public String alfabeto = "abcdefghijklmnopqrstuvwxyz";
    @Override
    public String criptografar(String texto) {
        //throw new UnsupportedOperationException("esse method nao esta implementado ainda");
        verifyText(texto);
        String text = texto.toLowerCase();
        for (int i =0; i < text.length();i++){
            boolean achou = false;
            for (int j =0; j < alfabeto.length();j++)
                if (text.charAt(i)==alfabeto.charAt(j)){
                    achou=true;
                    int indice = j+3;
                    criptografado += (indice >25) ? alfabeto.charAt(indice-25): alfabeto.charAt(indice);
            }
            if(achou == false){
                criptografado += text.charAt(i);
            }
        }
    return criptografado.toLowerCase();
    }

    @Override
    public String descriptografar(String texto) {
        //throw new UnsupportedOperationException("esse method nao esta implementado ainda");
        verifyText(texto);
        String text = texto.toLowerCase();
        for (int i =0; i < text.length();i++){
            boolean achou = false;
            for (int j =0; j < alfabeto.length();j++)
                if (text.charAt(i)==alfabeto.charAt(j)){
                    achou=true;
                    int indice = j-3;
                    descriptografado += (indice < 0) ? alfabeto.charAt(indice + 25): alfabeto.charAt(indice);
                }
            if(achou == false){
                descriptografado += text.charAt(i);
            }
        }
        return descriptografado.toLowerCase();
    }
    public void verifyText(String texto){
        if (texto == null) {
            throw new NullPointerException("The method argument mustn't be null.");
        }
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("The method agument can't be an empty string.");
        }
    }
}
