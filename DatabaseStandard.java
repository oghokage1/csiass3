import java.util.HashMap;

public class DatabaseStandard implements DatabaseInterface{
	HashMap<String, String> hmap;
	private int size;
	public DatabaseStandard(){
		hmap = new HashMap();
		size = 0;
	}
	 

	// Stores plainPassword and corresponding encryptedPassword in a map.
	// if there was a value associated with this key, it is replaced, 
	// and previous value returned; otherwise, null is returned
	// The key is the encryptedPassword the value is the plainPassword
	public String save(String plainPassword, String encryptedPassword){
		String toReturn = hmap.get(encryptedPassword);
		hmap.put(encryptedPassword, plainPassword);
		if(toReturn == null){
			size++;
		}
		return toReturn;
	} 
	
	// returns plain password corresponding to encrypted password
	public String decrypt(String encryptedPassword){
		return hmap.get(encryptedPassword);
	}
	
	// returns the number of password pairs stored in the database
	public int size(){
		return size; //change later
	}

    public void printStatistics(){
    		System.out.println("Size "+ size);
     // print statistics based on type of Database
	}
}