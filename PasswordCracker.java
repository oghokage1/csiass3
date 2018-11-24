import java.util.ArrayList;

public class PasswordCracker{
	
	public PasswordCracker(){

	}	

	public void createDatabase(ArrayList<String> commonPasswords, DatabaseInterface database){
		String holder;
		for(String p : commonPasswords){

			try{
				database.save(p, Sha1.hash(p));
				database.save(augment1(p), Sha1.hash(augment1(p)));
				database.save(augment2(p), Sha1.hash(augment2(p)));
				database.save(augment3(p), Sha1.hash(augment3(p)));
				database.save(augment4(p), Sha1.hash(augment4(p)));
				database.save(augment5(p), Sha1.hash(augment5(p)));
				database.save(augment6(p), Sha1.hash(augment6(p)));
				database.save(augmentLtrs(p), Sha1.hash(augmentLtrs(p)));
				database.save(augmentAll2(p), Sha1.hash(augmentAll2(p)));
				database.save(augmentAll(p), Sha1.hash(augmentAll(p)));
			}catch(Exception e){
				//what to do
			}
		}
	}

	//returns "" if nothing found, NOT NULL

	public String crackPassword(String encryptedPassword, DatabaseInterface database){

		String pw = database.decrypt(encryptedPassword);
		if(pw == null){
			return "";
		}else{
			return pw;
		}
	}



//string augmentng foncs

	//adds 2018 to end of string		
	public static String augment1(String password){
		
		return password + "2018";
	}

	//capitalizes first letter of string
	public static String augment2(String password){
		char[] charArray = password.toCharArray();
		charArray[0] = capitalize(charArray[0]);
		return new String(charArray);
	}

	//replaces a's with @'s'
	public static String augment3(String password){
		char[] charArray = password.toCharArray();
		replace(charArray, 'a', '@');
		return new String(charArray);
	}

	//replaces is with 1s
	public static String augment4(String password){
		char[] charArray = password.toCharArray();
		replace(charArray,'i', '1');

		return new String(charArray);
	}

	//replaces e's with 3's
	public static String augment5(String password){
		char[] charArray = password.toCharArray();
		replace(charArray, 'e', '3');
		return new String(charArray);
	}

/*
	public static String augment(String pw, int num){
		String result = pw; //bc strings are immutable!!
		if(num% 2 ==0 ){
			result = augment1(result);
		}
		if(num % 3 ==0 ){
			result = augment2(result);
		}
		if(num% 5 ==0 ){
			result = augment3(result);
		}
		if(num% 7 ==0 ){
			result = augment4(result);
		}
		if(num% 11 ==0 ){
			result = augment5(result);
		}
		return result;
	}

	*/


	//does all 3 letter replacements
	public static String augmentLtrs(String password){
		char[] charArray = password.toCharArray();

		replace(charArray, 'a', '@');
		replace(charArray, 'e', '3');
		replace(charArray,'i', '1');

		return new String(charArray);
	}

	//replaces letters and capitalizes first letter
	public static String augment6(String password){
		return augment2(augmentLtrs(password));
	}



	//does all augmentations
	public static String augmentAll(String password){
		String pw = password + "2018";
		char[] charArray = pw.toCharArray();
		charArray[0] = capitalize(charArray[0]);
		
		replace(charArray, 'a', '@');
		replace(charArray, 'e', '3');
		replace(charArray,'i', '1');

		return new String(charArray);
	}
		//does all augmentations in another order
	public static String augmentAll2(String password){
		String pw = password + "2018";
		char[] charArray = pw.toCharArray();
		
		replace(charArray, 'a', '@');
		replace(charArray, 'e', '3');
		replace(charArray,'i', '1');
		charArray[0] = capitalize(charArray[0]);

		return new String(charArray);
	}


	public static void replace(char[] letters, char replacee, char replacer){
		for(int i = 0; i<letters.length; i++){
			if(letters[i] == replacee){
				letters[i] = replacer;
			}
		}
	}

	public static char capitalize(char c){
		int ascOfC = (int)c;

		if(ascOfC>= 97 && ascOfC<=122){
			return (char)(ascOfC - 32);
		}else{
			return c;
		}

	}

}