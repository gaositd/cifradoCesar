package src.cipher;

import src.file.FileManager;
import src.validations.Validator;

public class Cipher {
  private static final char[] ALPHABET = {'a','á','à','ä','â','b','c','ç','d','e','é','è','ë','ê','f','g','h','i','í','ì','ï','î','j','k','l','m','n','ñ','o','ó','ò','ö','ô','p','q',
      'r','s','t','u','v','w','x','y','z','A','Á','À','Ä','Â','B','C','Ç','D','E','É','È','Ë','Ê','F','G','H','I','Í','Ì','Ï','Î','J','K','L','M','N','Ñ','O','Ó','Ò','Ö','Ô','P','Q',
      'R','S','T','U','V','W','X','Y','Z',' ','.',',','!','¿',';',':','-','_','+','=','*','/','%','&','@','#','$','^','`','~','|','\n','\t','1','2','3','4','5','6','7','8','9','0'};

  private static Cipher instance;

  public Cipher(){}

  public static Cipher getInstance(){
    if(instance == null)
        instance = new Cipher();

    return instance;
  }

  Validator validator = new Validator();
  public String encrypt(String text, int shift) throws Exception {
    if( !( validator.isValidKey(shift, getAlphabet() ) ) ){
      throw new Exception("La clave es inválida favor de revisar");
    }

    String strAlphabeth= new String(getAlphabet());
    StringBuilder cipherText = new StringBuilder();
    int sizeArray = getAlphabet().length;

    for(char character : text.toCharArray()){
      int index = strAlphabeth.indexOf(character);

      if(index != -1){
        int newPosition = (index + shift) % sizeArray;
        cipherText.append(strAlphabeth.charAt(newPosition));
      }else
        cipherText.append(character);

    }
    FileManager fileManager = new FileManager();
    fileManager.writeFile(cipherText.toString(), "encrypt.txt");
    return cipherText.toString();
  }

  public String decrypt(String encryptedText, int shift) {
    try {
      return encrypt(encryptedText, (shift*(-1)));
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

  }

  public char[] getAlphabet() {
    return ALPHABET;
  }
}
