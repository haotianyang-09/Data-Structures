public class HuffmanNode implements Comparable<HuffmanNode> {
    String letter;
    int frequency;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(String letter, int frequency){
        this.letter=letter;
        this.frequency=frequency;
        left=null;
        right=null;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right){
        this.left=left;
        this.right=right;
        letter=left.letter+right.letter;
        frequency=left.frequency+right.frequency;
    }

    

    public String toString(){
        return "<"+letter+", "+frequency+">";
    }

    @Override
    public int compareTo(HuffmanNode o) {
        if (frequency < o.frequency) {
            return -1; 
          }
           else if (frequency > o.frequency) {
            return 1;
          } else {
            return 0;
          }
    }
}
