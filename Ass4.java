public class Ass4{
	
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

	public static void main(String[] args){
		System.out.println(augment(args[0]));
	}
}
