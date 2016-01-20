package Assignment1;

/*
 * rewrite following String method.
 * length(), toUpperCase(), charAt(), replace(), substring(int), substring(),
 * startsWith(), endsWith(), insert(), delete(), deleteCharAt(), reverse(), 
 * setCharAt(), contains(), compareTo()
 */

public class Q14 {

	public static class MyString {
		String content;
		
		public MyString(String str) {
			this.content = str;
		}
		
		public int myLength(){
			char[] ch = this.content.toCharArray();
			return ch.length;
		}
		
		public String myToUpperCase(){
			char[] ch = this.content.toCharArray();
			String str = "";
			for (int i = 0; i < ch.length; i++) {
				if (ch[i] >= 'a' && ch[i] <= 'z') {
					ch[i] = (char) ((int) ch[i] % (int) 'a' + (int)'A');
				} 
				str += ch[i];
			}
			return str;	
		}
		
		public char myCharAt(int index) {
			char[] ch = this.content.toCharArray();
			if (index >= ch.length) {
				System.err.println("Method myCharAt() failed. Index exceeds the length of string.");
				return ' ';
			}
			return ch[index];
		}
		
		public String myReplace(char oldChar, char newChar) {
			if (oldChar != newChar) {
				String str = "";
				char[] ch = this.content.toCharArray();
				for (int i = 0; i < ch.length; i++) {
					if (ch[i]  == oldChar) {
						ch[i] = newChar;
					} 
					str += ch[i];
				}
				return new String(str);
			} 
			return this.content;
		}
		
		public String mySubstring(int ind) {
			if (ind != 0) {
				String str = "";
				char[] ch = this.content.toCharArray();
				if (ind < ch.length) {
					for (int i = ind; i < ch.length; i++) {
						str += ch[i];
					}
					return str;
				} else {
					System.err.println("Method mySubstring failed. Index exceeds string's length");
					return "";
				}
			}					
			return this.content;
		}
		
		public String mySubstring(int startInd, int endInd) {
			if (startInd < endInd) {
				String str = "";
				char[] ch = this.content.toCharArray();
				if (endInd <= ch.length) {
					for (int i = startInd; i < endInd; i++) {
						str += ch[i];
					}
					return str;
				} else {
					System.err.println("Method mySubstring failed. Index exceeds string's length");
					return "";
				}
			}
			System.out.println("Start point is not smaller than end point, please reset input args.");
			return this.content;
		}
		
		public boolean myStartsWith(String str) {
			char[] inputCh = str.toCharArray();
			char[] ch = this.content.toCharArray();
			if (inputCh.length <= ch.length) {
				int i = -1;
				while (++i < inputCh.length) {
					if (ch[i] != inputCh[i]) {
						return false;
					}
				}
			} else {
				System.out.println("The length of your argument exceeds the length of this string.");
				return false;
			}			
			return true;
		}
		
		public boolean myEndsWith(String str) {
			char[] inputCh = str.toCharArray();
			char[] ch = this.content.toCharArray();
			if (inputCh.length <= ch.length) {
				int i = 0;
				while (++i <= inputCh.length) {
					if (ch[ch.length - i] != inputCh[inputCh.length - i]) {
						return false;
					}
				}
			} else {
				System.out.println("The length of your argument exceeds the length of this string.");
				return false;
			}			
			return true;
		}
		
		public String myInsert(int ind, String inputStr) {
			String str = "";
			char[] ch = this.content.toCharArray();
			if (ind < ch.length) {
				for (int i = 0; i < ch.length; i++) {
					if (i == ind) {
						str += inputStr;
					}
					str += ch[i];
				}
				return str;
			} else {
				System.out.println("index exceeds string's length.");
			}
			return str;
		} 
		
		public String myDelete(int start, int end) {
			if (start < end) {
				String str = "";
				char[] ch = this.content.toCharArray();
				if (end <= ch.length) {
					for (int i = 0; i < ch.length; i++) {
						if (i < start || i >= end) {
							str += ch[i];
						}
					}
					return str;
				} else {
					System.err.println("Method mySubstring failed. Index exceeds string's length");
					return "";
				}
			}
			System.out.println("Start point is not smaller than end point, please reset input args.");
			return this.content;
		}
		
		public String myDeleteCharAt(int ind) {
			String str = "";
			char[] ch = this.content.toCharArray();
			if (ind < ch.length) {
				for (int i = 0; i < ch.length; i++) {
					if (i != ind) {
						str += ch[i];
					}
				}
				return str;
			}					
			System.err.println("Method myDeleteCharAt() failed. Index exceeds the length of string.");
			return "";
		}
		
		public String myReverse() {
			String str = "";
			char[] ch = this.content.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				str += ch[ch.length - i - 1];
			}
			return str;
		}
		
		public String mySetCharAt(int ind, char inputCh){
			String str = "";
			char[] ch = this.content.toCharArray();
			if (ind < ch.length) {
				if (ch[ind] != inputCh) {
					ch[ind] = inputCh;
					for (int i = 0; i < ch.length; i++) {
						str += ch[i];
					}
					return str;
				}
			return this.content;
			}
			System.err.println("Method mySetCharAt() failed. Index exceeds the length of string.");
			return "";
		}
		
		public boolean myContains(String inputStr) {
			char[] inputCh = inputStr.toCharArray();
			char[] ch = this.content.toCharArray();
			if (ch.length >= inputCh.length) {
				for (int i = 0; i <= ch.length - inputCh.length; i++) {
					if (inputStr.equals(this.mySubstring(i, i + inputCh.length))) {
						return true;
					}
				}
				return false;
			}
			System.err.println("Method myContains() failed. Length of input string exceeds the length of string.");
			return false;
		}
		
		public int myCompareTo(String inputStr) {
			char[] inputCh = inputStr.toCharArray();
			char[] ch = this.content.toCharArray();
			if (ch.length > inputCh.length){
				return ch.length - inputCh.length; 
			} else if (ch.length < inputCh.length) {
				return inputCh.length - ch.length;
			} else {
				for (int i = 0; i < ch.length; i++) {
					if (((int) ch[i] - (int) inputCh[i]) != 0) {
						return (int) ch[i] - (int) inputCh[i];
					} 
				}
				return 0;
			}		
		}	
	}

	public static void main(String[] args) {
		String str = "I have a dream.";
		MyString test = new MyString(str);
		
//		System.out.println(test.myLength());
//		System.out.println(str.length());
		
//		System.out.println(test.myToUpperCase());
//		System.out.println(str.toUpperCase());
		
//		System.out.println(test.myCharAt(10));
//		System.out.println(str.charAt(10));
		
//		System.out.println(test.myReplace('a', 'x'));
//		System.out.println(str.replace('a', 'x'));
		
//		System.out.println(test.mySubstring(17));
//		System.out.println(str.substring(10));

//		System.out.println(test.mySubstring(10, 15));
//		System.out.println(str.substring(10, 15));
		
//		System.out.println(test.myStartsWith("I have a "));
//		System.out.println(str.startsWith("I have a "));

//		System.out.println(test.myEndsWith("e a dream."));
//		System.out.println(str.endsWith("e a dream."));
		
//		System.out.println(test.myInsert(1 ,"####"));
		
//		System.out.println(test.myDelete(8, 13));
		
//		System.out.println(test.myDeleteCharAt(8));
		
//		System.out.println(test.myReverse());
		
//		System.out.println(test.mySetCharAt(3, 'x'));
		
//		System.out.println(test.myContains("I have a dream."));
//		System.out.println(str.contains("I have a dream."));
		
		System.out.println(test.myCompareTo("I have a Dream."));
		System.out.println(str.compareTo("I have a Dream."));		
	}
}
