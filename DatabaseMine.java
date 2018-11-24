public class DatabaseMine implements DatabaseInterface{

	public class Entry<K,V>{
		K key;
		V value;
		public Entry(K k, V v){
			key = k;
			value = v;
		}
	}

	private Entry<String, String>[] tableArray;
	private int capacity;
	private int size;

	public DatabaseMine(int capacity){
		tableArray = new Entry[capacity];
		this.capacity = capacity;
		size = 0;
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

	private String get(String key){
		int spot = place(key);

		while( isAvailable(spot) ){ //end condition?
			spot = nextSpot(spot);
		}

		return tableArray[spot].value;

	}

	private void put(String key, String value){
		int spot = place(key);

		while(!isAvailable(spot)){ ///put condition on this to end while full
			spot = nextSpot(spot);
		}

		tableArray[spot] = new Entry(key, value);
		size++;

	}


	private int hashCode(String key){
		return key.hashCode();

	}

	private int place(String key){
		return (hashCode(key)%capacity);
	}

	private int nextSpot(int p){

		int nextSpot = p+1;
		if(nextSpot>=capacity){
			nextSpot = nextSpot%capacity;
		}
		return nextSpot;
	}

	public boolean isAvailable(int spot){
		return tableArray[spot] == null;
	}
}