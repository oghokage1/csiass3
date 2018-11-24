public class PasswordCracker{
	

	public void createDatabase(ArrayList<String> commonPasswords, DatabaseInterface database){
		for(String p : commonPasswords){
			database.add(p, Sha1.hash(p));
			database.add(augment1(p), Sha1.hash(augment1(p)));
			database.add(augment2(p), Sha1.hash(augment2(p)));
			database.add(augment3(p), Sha1.hash(augment3(p)));
			database.add(augment4(p), Sha1.hash(augment4(p)));
			database.add(augment5(p), Sha1.hash(augment5(p)));
			database.add(augment6(p), Sha1.hash(augment6(p)));
			database.add(augmentAll(p), Sha1.hash(augmentAll(p)));
		}
	}

	public String crackPassword(String encryptedPassword, DatabaseInterface database){
		return database.decrypt(encryptedPassword);
	}



//string augmentng foncs		
	public static String augment1(String password){
		String pw = password + "2018";
		return new String(charArray);
	}

	public static String augment2(String password){
		char[] charArray = password.toCharArray();
		charArray[0] = capitalize(charArray[0]);
		return new String(charArray);
	}

	public static String augment3(String password){
		char[] charArray = password.toCharArray();
		replace(charArray, 'a', '@');
		return new String(charArray);
	}

	public static String augment4(String password){
		char[] charArray = password.toCharArray();
		replace(charArray,'i', '1');

		return new String(charArray);
	}

	public static String augment5(String password){
		char[] charArray = password.toCharArray();
		replace(charArray, 'e', '3');
		return new String(charArray);
	}

	public static String augment6(String password){
		char[] charArray = password.toCharArray();

		replace(charArray, 'a', '@');
		replace(charArray, 'e', '3');
		replace(charArray,'i', '1');

		return new String(charArray);
	}

	public static String augmentAll(String password){
		String pw = password + "2018";
		char[] charArray = pw.toCharArray();
		charArray[0] = capitalize(charArray[0]);
		
		replace(charArray, 'a', '@');
		replace(charArray, 'e', '3');
		replace(charArray,'i', '1');

		return new String(charArray);
	}

}