package ee.valja7.experiment.springboot.hello;

import org.mindrot.jbcrypt.BCrypt;

public class CreatePasswordHash {
    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println(BCrypt.hashpw(args[0], BCrypt.gensalt(12)));
        }
    }
}
