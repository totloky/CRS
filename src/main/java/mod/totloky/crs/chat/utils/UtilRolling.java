package mod.totloky.crs.chat.utils;

public class UtilRolling {

    public static String parseRoll (String roll) {
        String[] tokens = roll.split("d");
        StringBuilder Result = new StringBuilder();
        int random;
        int sum = 0;

        if (tokens[0].equals("") || tokens[0].equals("1")) /*Integer.parseInt(tokens[0])<=10)*/ {
            random = diceRolling(Integer.parseInt(tokens[1]));
            Result.append("[").append(random).append("]=").append(random);
        } else {
            if (Integer.parseInt(tokens[0]) <= 10) {
                Result.append("[");
                for (int i = Integer.parseInt(tokens[0]); i > 0; i--) {
                    random = diceRolling(Integer.parseInt(tokens[1]));
                    sum += random;
                    if (i == 1) {
                        Result.append(random).append("]=").append(sum);
                    } else {
                        Result.append(random).append(";");
                    }
                }
            } else {
                for (int i = Integer.parseInt(tokens[0]); i > 0; i--) {
                    random = diceRolling(Integer.parseInt(tokens[1]));
                    sum += random;
                }
                Result.append("{").append(sum).append("}=").append(sum);
            }
        }
        System.out.println(Result);
        return Result.toString();
    }

    public static int diceRolling(int rollEdge) {
        return (int) (Math.random() * rollEdge)+1;
    }
}
