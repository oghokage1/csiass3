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
	private int collisions;
	private int probes;

	public DatabaseMine(int capacity){
		tableArray = new Entry[capacity];
		this.capacity = capacity;
		size = 0;
		collisions = 0;
		probes++;
	}

	public DatabaseMine(){
		tableArray = new Entry[DEFAULT_SIZE];
		this.capacity = DEFAULT_SIZE;
		size = 0;
		collisions = 0;
		probes++;
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

    	System.out.println("Size is "+ size + " passwords");
    	System.out.println("Number of Indexes is "+capacity);
    	System.out.println("Load Factor is "+ (double)size/capacity);
    	System.out.println("Average Number of Probes is " + (double)probes/size);
    	System.out.println("Number of displacements (due to collisions) is " + collisions);
/*

    	*** DatabaseMine Statistics ***
Size is 20 passwords
Number of Indexes is 37
Load Factor is 0.5405405
Average Number of Probes is 1.5
Number of displacements (due to collisions) 4
*** End DatabaseMine Statistics ***
*/

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
		boolean displaced = false;

		for(int i = 0;  i< capacity; i++){

			if(tableArray[spot] != null && tableArray[spot].key.equals(key)){
				tableArray[spot].value = value; //replacing value
				break;
			}else if(tableArray[spot] == null){
				tableArray[spot] = new Entry(key, value);
				if(displaced){
					collisions++;
				}
				size++;
			}else{
				spot = (spot+1)%capacity;
				displaced = true;
				probes++;
			}
		}
		
	}


/*
returns hash of string
*/
	private int hash(String key){
		return java.lang.Math.abs((key.hashCode())%capacity);

	}

}