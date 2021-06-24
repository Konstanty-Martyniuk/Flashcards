class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        StringBuilder sb = new StringBuilder();
        return sb.append("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
    }

    public static void main(String[] args) {
        System.out.println(createEnglishAlphabet());
    }
}