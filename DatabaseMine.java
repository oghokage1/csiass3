import java.util.*;
public class DatabaseMine implements DatabaseInterface{

	public class Entry<K,V>{
		K key;
		V value;
		public Entry(K k, V v){
			key = k;
			value = v;
		}
	}

	private static final int DEFAULT_SIZE = 100000;

	private Entry<String, String>[] tableArray;
	private int capacity;
	private int size;

	public DatabaseMine(int capacity){
		tableArray = new Entry[capacity];
		this.capacity = capacity;
		size = 0;
	}

	public DatabaseMine(){
		tableArray = new Entry[DEFAULT_SIZE];
		this.capacity = DEFAULT_SIZE;
		size = 0;

		System.out.println(capacity);
	}

	// Stores plainPassword and corresponding encryptedPassword in a map.
	// if there was a value associated with this key, it is replaced, 
	// and previous value returned; otherwise, null is returned
	// The key is the encryptedPassword the value is the plainPassword
	public String save(String plainPassword, String encryptedPassword){

		String toReturn = get(encryptedPassword);
		put(encryptedPassword, plainPassword);
		return toReturn;
	} 
	
	// returns plain password corresponding to encrypted password
	public String decrypt(String encryptedPassword){
		return get(encryptedPassword);
	}
	
	// returns the number of password pairs stored in the database
	public int size(){
		return size; //change later
	}

    public void printStatistics(){
    		System.out.println("where the stats ould go if i finish");
     // print statistics based on type of Database
	}

/*
Returns the value to which the specified key is mapped, 
or null if this map contains no mapping for the key.
*/
	private String get(String key){

		int spot = hash(key);
		int i = 0;

		while( i< capacity ){
			if(tableArray[spot] == null){ //item doesnt exist in table!
				return null; 
			}

			if(!tableArray[spot].key.equals(key)){ //another element took our spot, we gotta start linear probing for our location!
				spot = (spot+1)%capacity;
			}else{
				break; //you have found your spot! now just return the associated value
			}

		}

		if(i == capacity){ //looked through whole tabke
			return null;
		}
		
		return tableArray[spot].value;
	}

/*
Associates the specified value with the specified key in this map. 
If the map previously contained a mapping for the key, the old value is replaced.
*/
	private void put(String key, String value){
		int spot = hash(key);
		//System.out.println("79 CAUGHT U B!!!!");


		for(int i = 0;  i< capacity; i++){

			if(tableArray[spot] != null && tableArray[spot].key.equals(key)){
				tableArray[spot].value = value; //replacing value
				break;
			}else if(tableArray[spot] == null){
				tableArray[spot] = new Entry(key, value);
			}else{
				spot = (spot+1)%capacity;
			}
		}
		
	}


	private int hash(String key){
		return java.lang.Math.abs((key.hashCode())%capacity);

	}

}