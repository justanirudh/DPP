package competitions_and_interviews;

/**
 * Created by paanir on 11/3/17.
 */
public class Grubhub1 {
//    Hi !
//
//    ROT13
//    A<->N
//    B<->O
//    E  R
//    C<->P
//    M<->Z
//
//            HELLO
//    URYYB
//
//‘HELLO’
//
//            ‘Hello’
//            ‘Hello, World!” -> ‘Uryyb, Jbeyq!’

//null, ””, “ABC”, “abc”, “Abc”, “!,;./’”, “Hello, World!”

    //precondition: valid string (a-z/ A-Z)
    String getROT13(String str) {
        if (str == null || str.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i); //e
            if (!Character.isAlphabetic(ch)) {
                sb.append(ch);
            } else {
                int newCh = ch + 13; //r
                if (Character.isUpperCase(ch)) {
                    if (newCh > 'Z')
                        sb.append(ch - 13); //B
                    else
                        sb.append(newCh); //URYY
                } else { //lowercase
                    if (newCh > 'z')
                        sb.append(ch - 13); //b
                    else
                        sb.append(newCh); //Uryyb
                }
            }
        }
        return sb.toString();
    }

}
