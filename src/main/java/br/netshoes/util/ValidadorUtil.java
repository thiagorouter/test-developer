package br.netshoes.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Thiago on 16/04/2015.
 */
public class ValidadorUtil {

        public static final String REGEX_CEP = "^\\d{8}$";

        public static boolean isValidCEP(final String cep) {

                Pattern pattern = Pattern.compile(REGEX_CEP);

                Matcher matcher = pattern.matcher(cep);
                return matcher.find();
        }
}
