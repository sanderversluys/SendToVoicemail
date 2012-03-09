package be.niob.apps.sendtovoicemail;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

public class SendToVoicemailActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		testGroup();
		//testContact();
	}
	
	private void testGroup() {
		ContentResolver cr = getContentResolver();
		Cursor cur = cr.query(ContactsContract.Groups.CONTENT_URI, null, null, null, null);
		if (cur.getCount() > 0) {
			while (cur.moveToNext()) {
				String id = cur.getString(cur.getColumnIndex(ContactsContract.Groups._ID));
				String title = cur.getString(cur.getColumnIndex(ContactsContract.Groups.TITLE));
				
				Log.d("STV", String.format("%s %s", id, title));
			}
		}
	}

	private void testContact() {

		ContentResolver cr = getContentResolver();
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		if (cur.getCount() > 0) {
			while (cur.moveToNext()) {
				String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
				String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				boolean sendToVoicemail = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.SEND_TO_VOICEMAIL)) == 1;
				
				if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					if (sendToVoicemail)
						Log.d("STV", String.format("%s %s %s", id, name, sendToVoicemail));
				}
			}
		}

		// ContentValues values = new ContentValues();
		// alues.put(Phone.RAW_CONTACT_ID, rawContactId);
		// values.put(Phone.NUMBER, phoneNumber);
		// values.put(Phone.TYPE, Phone.TYPE_MOBILE);
		// Uri uri = getContentResolver().insert(Phone.CONTENT_URI, values);
	}
}