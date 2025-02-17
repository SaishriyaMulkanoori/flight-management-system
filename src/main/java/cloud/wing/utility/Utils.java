package cloud.wing.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Utils {

	public static String generateSalt() {

		String characters = "qwertyuiopasdfghjklzxcvbnm123456789";
		StringBuilder randomString = new StringBuilder(10);
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < 10; i++) {

			int randomIndex = random.nextInt(characters.length());
			randomString.append(characters.charAt(randomIndex));
		}
		return randomString.toString();

	}

	public static String generateOTP() {
		Random random = new Random();
		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			otp.append(random.nextInt(10));
		}
		return otp.toString();
	}

	// Helper method to convert bytes to hexadecimal representation
	private static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(2 * bytes.length);
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static String generateHash(String inputString) {

		String strHash = "";
		try {
			// Create a SHA-256 message digest instance
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Calculate the hash of the input string
			byte[] hashBytes = digest.digest(inputString.getBytes(StandardCharsets.UTF_8));

			// Convert the hash bytes to a hexadecimal representation
			strHash = bytesToHex(hashBytes);

			// System.out.println("Full Hash (64 characters): " + strHash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return strHash;

	}

	public static String generateBookingReference() {
		String prefix = "BK";
		int length = 8 - prefix.length();
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder reference = new StringBuilder(prefix);

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			reference.append(characters.charAt(index));
		}

		return reference.toString();

	}
}
