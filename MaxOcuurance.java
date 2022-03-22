import java.util.ArrayList;
import java.util.Scanner;

public class MaxOcuurance  {
    static class HashNode<Key, Value> {
        Key key1;
        Value value1;
        HashNode<Key, Value> next;
        public HashNode(Key key1, Value value1) {
            this.key1 = key1;
            this.value1 = value1;
        }
    }
    static class CustomHashTable<Key, Value> {//implemented a Hash Table named CustomHashTable 
        private ArrayList<HashNode<Key, Value>> bucket_array;
        private int num_of_Buckets;
        private int length;
        public CustomHashTable() {
            bucket_array = new ArrayList<>();
            num_of_Buckets = 10;
            length = 0;
            for (int i = 0; i < num_of_Buckets; i++)
                bucket_array.add(null);
        }
        public int size() {
            return length;
        }
        public boolean isEmpty() {
            return size() == 0;
        }
        private int getBucketIndex(Key key1) {
            int hashCode = key1.hashCode();
            int index = hashCode % num_of_Buckets;
            return index;
        }
        //removing key from hash table 
        public Value remove(Key key1) {
            int bucketIndex = getBucketIndex(key1);
            // Get head of chain
            HashNode<Key, Value> head = bucket_array.get(bucketIndex);
            // Search for key in its chain
            HashNode<Key, Value> prev = null;
            while (head != null) {
                // If Key found 
                if (head.key1.equals(key1))
                    break;
                // Else keep moving in chain 
                prev = head;
                head = head.next;
            }
            if (head == null)
                return null;
            length--;
            // Removing key from hashtable
            if (prev != null)
                prev.next = head.next;
            else
                bucket_array.set(bucketIndex, head.next);
            return head.value1;
        }
        public Value get(Key key1) {
            // Find head of chain for given key
            int bucketIndex = getBucketIndex(key1);
            HashNode<Key, Value> head = bucket_array.get(bucketIndex);
            // Search key in chain
            while (head != null) {
                if (head.key1.equals(key1))
                    return head.value1;
                head = head.next;
            }
            // when key is  found 
            return null;
        }
        public void add(Key key1, Value value1) {
            // Finding a head of chain for given key 
            int bucketIndex = getBucketIndex(key1);
            HashNode<Key, Value> head = bucket_array.get(bucketIndex);
            // Check if the key is already present
            while (head != null) {
                if (head.key1.equals(key1)) {
                    head.value1 = value1;
                    return;
                }
                head = head.next;
            }
            // Insert key in chain 
            length++;
            head = bucket_array.get(bucketIndex);
            HashNode<Key, Value> newNode = new HashNode<Key, Value>(key1, value1);
            newNode.next = head;
            bucket_array.set(bucketIndex, newNode);
        }
    }

    public static void main(String args[])
    {
        CustomHashTable<Character, Integer> ht = new CustomHashTable<>();
        Scanner input = new Scanner(System.in);

        System.out.println("This program helps to findout the character which occurs maximum number of times in the input string.\n");
        //Addressed user about what will happen in this code 
        int n = 0;
        String str;
        do {
            System.out.print("Input the string which you want to check: ");
            //Get the input from user
            //*The input will contain a string.
            //*The maximum length of the string can be 1000.
            str = input.nextLine();
            n = str.length();
        } while (n > 1000);
        //creating a char type array to take all characters given by user into Hash Table
        char[] chararray = str.toCharArray();
        //hashtable
        for (int i = 0; i < n; i++ ){
            ht.add(chararray[i], (int) chararray[i]);
        }
        int maximum = 0;
        int total = 0;
        int value = 0;
        for (int i = 0; i < n; i++) {
            total = 0;
            for (int j = 0; j < n; j++) {
                if ( ht.get(chararray[j]) == ht.get(chararray[i]) ) {
                    total++;
                }
            }
            if (total > maximum) {
                value = ht.get(chararray[i]);
                maximum = total;
            }
            else if (total == maximum) {
                if (ht.get(chararray[i]) < value) {
                    value = ht.get(chararray[i]);
                }
                maximum = total;
            }
        }
        char count = '0';
        for (int i = 0; i < n; i++) {
            if (value == ht.get(chararray[i])) {
                count = chararray[i];
                break;
            }
        }
        System.out.println( count + " " + maximum );
        
           
    }
}
