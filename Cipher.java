// This program allows the cipher to be created.

public class Cipher 
{
    // original alphabet and cipher alphabet
    private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static char[] codedAlphabet;

    public static void setup(String key)
    {
        codedAlphabet = new char[26];
        String finalKey = "";
        // gets rid of any repeating letters in the keyword
        for (int i = 0; i < key.length(); i++)
        {
	        if (finalKey.indexOf(key.charAt(i)) == -1 && Character.isLetter(key.charAt(i)))
		        finalKey += key.charAt(i);
        }
        // adds key word to cipher
        for (int i = 0; i < finalKey.length(); i++)
	        codedAlphabet[i] = Character.toUpperCase(finalKey.charAt(i));
        // adds rest of the alphabet to cipher
        int j = finalKey.length() - 1;
        for (int i = 0; i < alphabet.length && j < codedAlphabet.length; i++)
        {
            if (indexOf(codedAlphabet, alphabet[i]) == -1)
	        {
		        codedAlphabet[j] = alphabet[i];
		        j++;
	        }
        }
    }

    public static String encrypt(String key, String message)
    {
        setup(key);
        String encodedMessage = "";
        // loops through message input
        for (int i = 0; i < message.length(); i++)
        {
	        char currentLetter = message.charAt(i);
            // ignores non-letter characters
	        if (!Character.isLetter(currentLetter))
		        encodedMessage += currentLetter;
	        else
	        {
		        // matches index of letter in the original alphabet array 
                // with the letter at the same index in the cipher alphabet
                int index = indexOf(alphabet, Character.toUpperCase(currentLetter));
		        if (index == -1)
			        return "Error";
		        else
		        {
			        if (Character.isLowerCase(currentLetter))
				        encodedMessage += Character.toLowerCase(codedAlphabet[index]);
			        else
				        encodedMessage += codedAlphabet[index];
		        }
	        }
        }
        return encodedMessage;
    }

    public static String decrypt(String key, String encodedMessage)
    {
        setup(key);
        String message = "";
        // loops through message input
        for (int i = 0; i < encodedMessage.length(); i++)
        {
	        char currentLetter = encodedMessage.charAt(i);
            // ignores non-letter characters
	        if (!Character.isLetter(currentLetter))
		        message += currentLetter;
	        else
	        {
		        // matches index of letter in the cipher alphabet array 
                // with the letter at the same index in the original alphabet
                int index = indexOf(codedAlphabet, Character.toUpperCase(currentLetter));
		        if (index == -1)
			        return "Error";
		        else
		        {
			        if (Character.isLowerCase(currentLetter))
				        message += Character.toLowerCase(alphabet[index]);
			        else
				        message += alphabet[index];
		        }
	        }
        }
        return message;
    }

    public static int indexOf(char[] arr, char ch)
    {
        // indexOf function with char variables
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == ch)
                return i;
        }
        return -1;
    }
}
