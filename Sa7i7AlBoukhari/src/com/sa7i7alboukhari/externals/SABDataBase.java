package com.sa7i7alboukhari.externals;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.sa7i7alboukhari.entity.Chapter;
import com.sa7i7alboukhari.entity.Hadith;

/**
 * AlMoufasserAlSaghir
 * @author HICHEM LAROUSSI - RAMI TRABELSI
 * Copyright (c) 2014 Zad Group. All rights reserved.
 */

public class SABDataBase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "bou5ari.db";
    private static final int DATABASE_VERSION = 1;
    
    private Context context;

    public SABDataBase(Context context) {
    	super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        
    }
    
    
    
    public ArrayList<Chapter> getAllBabs() {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String sqlTables = "BABTable";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, null, null, null, null, null, null);

		ArrayList<Chapter> babs = new ArrayList<Chapter>();
		if(c.moveToFirst()){
			do{
				Chapter chapter = new Chapter();
				chapter.setId(c.getInt(1));
				chapter.setName(c.getString(2));
				chapter.setBookId(c.getInt(3));
				
				Log.i("", chapter.toString());
				
				babs.add(chapter);
				
			}while(c.moveToNext());
		}
		
		c.close();
		return babs;

	}
    
    public ArrayList<Hadith> getAllHadithsWithPage(int page) {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String sqlTable = "HadithTable";
		
		String whereClause = "page = ?";
		String[] whereArgs = {String.valueOf(page)};
		
		qb.setTables(sqlTable);
		Cursor c = qb.query(db, null, whereClause, whereArgs, null, null, null);

		ArrayList<Hadith> ahadith = new ArrayList<Hadith>();
		if(c.moveToFirst())
			do{
				Hadith hadith = new Hadith();
				hadith.setId(c.getInt(0));
				hadith.setTitleId(c.getInt(1));
				hadith.setText(formatHadith(c.getString(2)));
				hadith.setLink(c.getString(3));
				hadith.setFile(c.getString(4));
				hadith.setFavorite(c.getInt(5) == 1 ? true:false);
				hadith.setDownload(c.getInt(6) == 1 ? true:false);
				hadith.setPageId(c.getInt(7));
				
				Log.i("", hadith.toString());

				ahadith.add(hadith);
			}while (c.moveToNext());
		
		c.close();
		return ahadith;

	}
    
    public ArrayList<Hadith> getAllHadithsWithBabId(int bab) {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String sqlTable = "HadithTable";
		
		String whereClause = "BABID = ?";
		String[] whereArgs = {String.valueOf(bab)};
		
		qb.setTables(sqlTable);
		Cursor c = qb.query(db, null, whereClause, whereArgs, null, null, null);

		ArrayList<Hadith> ahadith = new ArrayList<Hadith>();
		if(c.moveToFirst())
			do{
				Hadith hadith = new Hadith();
				hadith.setId(c.getInt(0));
				hadith.setTitleId(c.getInt(1));
				hadith.setText(formatHadith(c.getString(2)));
				hadith.setLink(c.getString(3));
				hadith.setFile(c.getString(4));
				hadith.setFavorite(c.getInt(5) == 1 ? true:false);
				hadith.setDownload(c.getInt(6) == 1 ? true:false);
				hadith.setPageId(c.getInt(7));
				
				Log.i("", hadith.toString());

				ahadith.add(hadith);
			}while (c.moveToNext());
		
		c.close();
		return ahadith;

	}

    public ArrayList<Hadith> getFavoriteHadiths() {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String sqlTable = "HadithTable";
		
		String whereClause = "IsFavorite = ?";
		String[] whereArgs = {String.valueOf(1)};
		
		qb.setTables(sqlTable);
		Cursor c = qb.query(db, null, whereClause, whereArgs, null, null, null);

		ArrayList<Hadith> ahadith = new ArrayList<Hadith>();
		if(c.moveToFirst())
			do{
				Hadith hadith = new Hadith();
				hadith.setId(c.getInt(0));
				hadith.setTitleId(c.getInt(1));
				hadith.setText(formatHadith(c.getString(2)));
				hadith.setLink(c.getString(3));
				hadith.setFile(c.getString(4));
				hadith.setFavorite(c.getInt(5) == 1 ? true:false);
				hadith.setDownload(c.getInt(6) == 1 ? true:false);
				hadith.setPageId(c.getInt(7));
				
				Log.i("", hadith.toString());

				ahadith.add(hadith);
			}while (c.moveToNext());
		
		c.close();
		return ahadith;

	}
    
    public ArrayList<Hadith> searchHadithWithText(String toSearchText) {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String sqlTable = "HadithTable";
		
		String whereClause = "HadithText LIKE ?";
		String[] whereArgs = {"%" + toSearchText + "%"};
		
		qb.setTables(sqlTable);
		Cursor c = qb.query(db, null, whereClause, whereArgs, null, null, null);

		ArrayList<Hadith> ahadith = new ArrayList<Hadith>();
		if(c.moveToFirst())
			do{
				Hadith hadith = new Hadith();
				hadith.setId(c.getInt(0));
				hadith.setTitleId(c.getInt(1));
				hadith.setText(formatHadith(c.getString(2)));
				hadith.setLink(c.getString(3));
				hadith.setFile(c.getString(4));
				hadith.setFavorite(c.getInt(5) == 1 ? true:false);
				hadith.setDownload(c.getInt(6) == 1 ? true:false);
				hadith.setPageId(c.getInt(7));
				
				Log.i("", hadith.toString());

				ahadith.add(hadith);
			}while (c.moveToNext());
		
		c.close();
		return ahadith;

	}
    
    public boolean setFavoriteHadith(int hadithId, boolean isFavorite){    	
    	SQLiteDatabase db = getWritableDatabase();

		String sqlTable = "HadithTable";
		
		ContentValues values = new ContentValues();
		values.put("IsFavorite", isFavorite ? 1:0);
		
		String whereClause = "ID = ?";
		String[] whereArgs = {String.valueOf(hadithId)};
		
		long updatedRow = db.update(sqlTable, values, whereClause, whereArgs);
		
		return updatedRow > 0;
    }
    
    public boolean isHadithFavorite(int hadithId) {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"IsFavorite"}; 
		String sqlTables = "HadithTable";
		
		String whereClause = "ID = ?";
		String[] whereArgs = {String.valueOf(hadithId)};

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, whereClause, whereArgs, null, null, null);

		boolean isFav = false;
		if(c.moveToFirst()){
			isFav = c.getInt(0) == 1 ? true:false;
		}
		
		c.close();
		return isFav;

	}
    
    public boolean setPathDownloadHadith(int hadithId, String path){    	
    	SQLiteDatabase db = getWritableDatabase();

		String sqlTable = "HadithTable";
		
		ContentValues values = new ContentValues();
		values.put("IsDownload", 1);
		values.put("SoundfilePath", path);
		
		String whereClause = "ID = ?";
		String[] whereArgs = {String.valueOf(hadithId)};
		
		long updatedRow = db.update(sqlTable, values, whereClause, whereArgs);
		
		return updatedRow > 0;
    }
    
    private String formatHadith(String text){
    	String header = "<span lang=\"AR\" dir=\"RTL\" style=\"font-size:13.5pt; font-family:\"Simplified Arabic\";mso-ascii-font-family:\"Times New Roman\"; mso-fareast-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\"; mso-ansi-language:EN-US;mso-fareast-language:EN-US;mso-bidi-language:AR\">";
    	String tail = "</span>";
    	
    	return header + text + tail;
    }
}