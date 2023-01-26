public class Operation {
    
    
    public static double calculation(String s) {
        return new Object() {
            int pos = -1 , ch ;
            void nextChar() {
                ch = (++pos <s.length()) ? s.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while(ch == ' ')nextChar();
                if(ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse() {
                nextChar();
                double x = parseExpression();
                if(pos < s.length()) throw new RuntimeException("Unexpected: "+ (char)ch);
                return x;
            }
            double parseExpression() {
                double x = parseTerm();
                for(; ;) {
                    if (eat('+')) x+= parseTerm();
                    else if(eat('-')) x-= parseTerm();
                    else return x;
                }
            }
            double parseTerm() {
                double x = parseFactor();
                for( ;  ;) {
                    if(eat('*')) x*= parseFactor();
                    else if(eat('/')) x/= parseFactor();
                    else return x;
                }
            }
            double parseFactor() {
                if(eat('+')) return +parseFactor();
                if(eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if(eat('(')) {
                    x = parseExpression();
                    if(!eat(')')) throw new RuntimeException("Missing ')'");

                }
                else if((ch >= '0' && ch <= '9')|| ch == '.') {
                    while((ch >='0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(s.substring(startPos, this.pos));
                }
                else if((ch >= 'a' && ch <= 'z')) {
                    while((ch >= 'a' && ch <= 'z')) nextChar();
                    String func = s.substring(startPos, this.pos);
                    if(eat('(')) {
                        x = parseExpression();
                        if(!eat(')')) throw new RuntimeException("Missing ')' after argument to "+func);
                    }
                    else {
                        x = parseFactor();
                    }
                    if(func.equals("log")) x = Math.log(x);
                    else if(func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if(func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if(func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown Function: "+(char)ch);
                }
                else {
                    throw new RuntimeException("Unexpected: "+(char)ch);
                }
                if(eat('^')) x = Math.pow(x, parseFactor());

                return x;
            } 
        }.parse();

    }
    public static void main(String[] args) {
        
        String s = "log25";
        System.out.println(calculation(s));
    }
}
