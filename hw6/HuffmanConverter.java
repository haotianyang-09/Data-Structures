import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

//constructor
public class HuffmanConverter{
 
        // ASCII number of characters
        public static final int NUMBER_OF_CHARACTERS = 256;

        private String contents;
        private static HuffmanTree huffmanTree;
        private int count[];
        private String code[];

        // Construct using an input string.
        // Initialize count and code.
        public HuffmanConverter(String input) {
            this.contents = input;
            this.count = new int[NUMBER_OF_CHARACTERS];
            this.code = new String[NUMBER_OF_CHARACTERS];
        }

        // Record how often each character occurs and store in count.
        public void recordFrequencies() {
            for (int i = 0; i < contents.length(); i++) {
                char c = contents.charAt(i);
                count[(int)c]++; 
            }

            for(int i=0;i<count.length;i++){
                if(count[i]>0 && (char)(i)!='\n'){
                    System.out.print("<"+(char)(i)+","+count[i]+"> ");
                }
                if(count[i]>0 && (char)(i)=='\n'){
                    System.out.print("<"+"\\n"+","+count[i]+"> ");
                }
            }

            System.out.println();
        }
        //Since the notation of going left and going right are different, the order of the output is also different, but the values are the same.

        // Construct a Huffman tree from count and 
        // store the tree in huffmanTree.
        public void frequenciesToTree() {
            BinaryHeap heap = new BinaryHeap();
            for(int i = 0; i < count.length; i++) {
                if(count[i] > 0) {
                    HuffmanNode node = new HuffmanNode("" + (char)i, (int)(count[i])); 
                    heap.insert(node);
                }
            }
            
            huffmanTree=HuffmanTree.createFromHeap(heap);
            huffmanTree.printLegend();
            //One output changes line for each letter, the other does not. I assume we need to change line after computing each letter.
        }

        // Construct code from huffmanTree.
        public void treeToCode() {
            for (int i = 0; i < code.length; i++) {
                code[i] = "";
            }
            treeToCode(huffmanTree.root, "");
        }

        private void treeToCode(HuffmanNode t, String encoding) {
            
            if (t.left == null && t.right == null) {
                code[(int)t.letter.charAt(0)] = encoding;
                return;
            }
    
            if (t.left != null) {
                treeToCode(t.left, encoding + "0");
            }
    
            if (t.right != null) {
                treeToCode(t.right, encoding + "1");
            }
        }

        // Encode content using code.
        public String encodeMessage() {
            StringBuilder encodedText = new StringBuilder();
            for (char c : contents.toCharArray()) {
                encodedText.append(code[(int) c]);
            }
            return encodedText.toString();
        }
        
        // Decode a Huffman encoding.
        public String decodeMessage(String encodedStr) {
            StringBuilder decodedText = new StringBuilder();
            HuffmanNode current = huffmanTree.root;

        for (char bit : encodedStr.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } 
            else if (bit == '1') {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedText.append(current.letter);
                current = huffmanTree.root; 
            }
        }

        return decodedText.toString();
    }

        // Read an input file.
        public static String readContents(String filename) {
            String temp = "";
            try {
                File file = new File(filename);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    temp += sc.nextLine();
                    temp += "\n";
                }
                sc.close();
                return temp;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return "";
        }

        public static void main(String args[]) {
                String input = HuffmanConverter.readContents(args[0]);
                HuffmanConverter h = new HuffmanConverter(input);
                h.recordFrequencies();
                // Print a list of characters and frequencies here!
                System.out.println();

                h.frequenciesToTree();
                
                h.treeToCode();
                // Print the Huffman encoding here!
                System.out.println();
                System.out.println("Huffman Encoding:");
                String encoded = h.encodeMessage();
                System.out.println(encoded+"\n");
                //0's and 1's are swapped everywhere because of different methods of coding
                System.out.println("Message size in ASCII encoding: "+h.contents.length()*8);
                System.out.println("Message size in Huffman coding: "+encoded.length()+"\n");
                System.out.println(h.decodeMessage(encoded));
        }

}
