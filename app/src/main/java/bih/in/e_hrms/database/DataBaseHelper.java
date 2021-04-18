package bih.in.e_hrms.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import bih.in.e_hrms.entity.Block;
import bih.in.e_hrms.entity.District;
import bih.in.e_hrms.entity.PanchayatData;
import bih.in.e_hrms.entity.PanchayatEntity;
import bih.in.e_hrms.entity.PanchayatWeb;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.entity.VillageListEntity;
import bih.in.e_hrms.entity.ward;

public class DataBaseHelper extends SQLiteOpenHelper {
    //private static String DB_PATH = "";
    private static String DB_PATH = "/data/data/app.bih.in.nic.epacsmgmt/databases/";
    //private static String DB_NAME = "eCountingAC.sqlite";
    private static String DB_NAME = "PACSDB1";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    SQLiteDatabase db;

    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 2);
        if (android.os.Build.VERSION.SDK_INT >= 4.2) {


            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;
    }



    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist


        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            //this.getReadableDatabase();

            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);


        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;

    }

    public boolean databaseExist() {


        File dbFile = new File(DB_PATH + DB_NAME);

        return dbFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        this.getReadableDatabase().close();
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();


    }

    public void openDataBase() throws SQLException {

        // Open the database
        this.getReadableDatabase();
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //modifyTable();
    }

    public void modifyTable(){

        if(isColumnExists("Ward", "AreaType") == false){
            AlterTable("Ward", "AreaType");
        }
    }


    public void AlterTable(String tableName, String columnName)
    {
        db = this.getReadableDatabase();

        try{

            db.execSQL("ALTER TABLE "+tableName+" ADD COLUMN "+columnName+" TEXT");
            Log.e("ALTER Done",tableName +"-"+ columnName);
        }
        catch (Exception e)
        {
            Log.e("ALTER Failed",tableName +"-"+ columnName);
        }
    }

    public boolean isColumnExists (String table, String column) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("PRAGMA table_info("+ table +")", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                if (column.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        cursor.close();
        return false;
    }
    public long getUserCount() {

        long x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("Select * from UserLogin", null);

            x = cur.getCount();

            cur.close();
            db.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return x;
    }

    public long insertSurfaceUserDetails(UserDetails result) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();

//            values.put("UserID", result.getUserID());
//            values.put("UserName", result.getName());
//            values.put("UserPassword", result.getPassword());
//            //values.put("IMEI", result.getIMEI());
//            values.put("RoleId", result.getUserroleId());
//            values.put("Role", result.getUserrole());
//
//            values.put("MobileNo", result.getMobileNo());
//            values.put("Email", result.getEmail());


            String[] whereArgs = new String[]{result.getUserID()};

            c = db.update("UserDetail", values, "UserID=? ", whereArgs);

            if (!(c > 0)) {

                c = db.insertWithOnConflict("UserDetail", null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return c;

    }

    public UserDetails getSurfaceUserDetails(String userId, String pass) {

        UserDetails userInfo = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{userId, pass};

            Cursor cur = db.rawQuery(
                    "Select * from UserDetail WHERE UserID=? and UserPassword=?",
                    params);
            int x = cur.getCount();

            while (cur.moveToNext()) {


//                userInfo = new UserDetails();
//                userInfo.setUserID(cur.getString(cur.getColumnIndex("UserID")));
//                userInfo.setName(cur.getString(cur.getColumnIndex("UserName")));
//                userInfo.setPassword(cur.getString(cur.getColumnIndex("UserPassword")));
//
//                userInfo.setMobileNo(cur.getString(cur.getColumnIndex("MobileNo")));
//                userInfo.setEmail(cur.getString(cur.getColumnIndex("Email")));
//
//                userInfo.setUserroleId(cur.getString(cur.getColumnIndex("RoleId")));
//                userInfo.setUserrole(cur.getString(cur.getColumnIndex("Role")));
//                userInfo.setAuthenticated(true);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            userInfo = null;
        }
        return userInfo;
    }

    public long setDistrictToLocal(ArrayList<District> list) {
        // String tableName = type == "pond" ? "PondInspectionDetail" : "WellInspectionDetail";

        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<District> info = list;


        if (info != null) {
            try {
                SQLiteDatabase db = this.getReadableDatabase();

                ContentValues values = new ContentValues();

                for (int i = 0; i < info.size(); i++) {

                    values.put("DistCode", info.get(i).get_DistCode());
                    values.put("DistName", info.get(i).get_DistName());
                    values.put("DistCode3", info.get(i).get_DistNameHN());
                    values.put("Zone", info.get(i).get_DistNameHN());
                    values.put("Circle", info.get(i).get_DistNameHN());


                    String[] whereArgs = new String[]{String.valueOf(info.get(i).get_DistCode())};

                    c = db.update("DistrictMwrd", values, "DistCode=?", whereArgs);

                    if(c != 1){
                        c = db.insert("DistrictMwrd", null, values);
                    }



                }
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setPanchayatDataToLocal(UserDetails userInfo, ArrayList<PanchayatEntity> list) {
        // String tableName = type == "pond" ? "PondInspectionDetail" : "WellInspectionDetail";

        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<PanchayatEntity> info = list;


        if (info != null) {
            try {
                SQLiteDatabase db = this.getReadableDatabase();

                ContentValues values = new ContentValues();

                for (int i = 0; i < info.size(); i++) {

                    values.put("PanchayatCode", info.get(i).getPcode());
                    values.put("PanchayatName", info.get(i).getPname());
                    values.put("PACName", info.get(i).getAreaType());

//                    values.put("BlockCode", userInfo.getBlockCode());
//                    //values.put("Block Name", userInfo.getBlockName());
//                    values.put("DistrictCode", userInfo.getDistrictCode());
                    values.put("DistrictName", userInfo.getDistName());
                    values.put("PartNo", "2");

                    String[] whereArgs = new String[]{String.valueOf(info.get(i).getPcode())};

                    c = db.update("Panchayat", values, "PanchayatCode=?", whereArgs);

                    if(c != 1){
                        c = db.insert("Panchayat", null, values);
                    }



                }
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public String getWellEncrhmntUpdatedDataCount(){
        String pondCount = "0", wellCount = "0";
        String[] params = new String[]{"1"};
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor curPond = db.rawQuery("Select * from CoWellEncroachmentReport WHERE isUpdated=?", params);

            pondCount = String.valueOf(curPond.getCount());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return pondCount;
    }

    public String getPondEncrhmntUpdatedDataCount(){
        String pondCount = "0", wellCount = "0";
        String[] params = new String[]{"1"};
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor curPond = db.rawQuery("Select * from CoPondEncroachmentReport WHERE isUpdated=?", params);
            pondCount = String.valueOf(curPond.getCount());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return pondCount;
    }

    public String getSchemeInspectionUpdatedDataCount(){
        String pondCount = "0";
        String[] params = new String[]{"1"};
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor curPond = db.rawQuery("Select * from SurfaceSchemeDetail WHERE Updated=?", params);

            pondCount = String.valueOf(curPond.getCount());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return pondCount;
    }

    public int getSchemeDataCount(){
        int pondCount = 0;
        String[] params = new String[]{"0"};
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor curPond = db.rawQuery("Select * from SurfaceSchemeDetail WHERE Updated=?", params);

            pondCount = curPond.getCount();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return pondCount;
    }

    public String getPlantationUpdatedDataCount(){
        //ArrayList<String> List = new ArrayList<String>();
        String pondCount = "0";
        String[] params = new String[]{"1"};
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor curPond = db.rawQuery("Select * from PlantationDetail WHERE isUpdated=?", params);

            pondCount = String.valueOf(curPond.getCount());
            //wellCount = String.valueOf(curWell.getCount());


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return pondCount;
    }

    public String getWellUpdatedDataCount(){
        String wellCount = "0";
        String[] params = new String[]{"1"};
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            //Cursor curPond = db.rawQuery("Select * from PondInspectionDetail WHERE isUpdated=?", params);

            Cursor curWell = db.rawQuery("Select * from WellInspectionDetail WHERE isUpdated=?", params);


            wellCount = String.valueOf(curWell.getCount());


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return wellCount;
    }

    public String getManregadDataCount(){
        String wellCount = "0";
        String[] params = new String[]{"1"};
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            //Cursor curPond = db.rawQuery("Select * from PondInspectionDetail WHERE isUpdated=?", params);

            Cursor curWell = db.rawQuery("Select * from Menrega_Rural_Dev_Dept WHERE isUpdated=?", params);


            wellCount = String.valueOf(curWell.getCount());


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return wellCount;
    }

    public String getOtherSchemeDataCount(){
        String wellCount = "0";
        String[] params = new String[]{"1"};
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            //Cursor curPond = db.rawQuery("Select * from PondInspectionDetail WHERE isUpdated=?", params);

            Cursor curWell = db.rawQuery("Select * from OtherDept_Of_Rural_Dev_Dept WHERE isUpdated=?", params);


            wellCount = String.valueOf(curWell.getCount());


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return wellCount;
    }

    public long deletePendingUpload2(String pid, String userId) {
        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(pid), userId};
            c = db.delete("UploadDataforGps", "sl_no=? and userId=?", DeleteWhere);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long deletePendingUpload3(String pid, String userId) {
        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(pid), userId};
            c = db.delete("SevikaSahaika", "slno=? and userId=?", DeleteWhere);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }


    public int getNumberOfPendingData(String userId) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};
            Cursor cur = db.rawQuery("Select slno from Inspection where Latitude IS NOT NULL and Longitude IS NOT NULL and UploadBy =?", whereArgs);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }

    public int getNumberTotalOfPendingData(String userId) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};
            Cursor cur = db.rawQuery("Select slno from UploadData where EntryBy =?", whereArgs);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }

    public int getNumberOfPendingData2(String userId) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};
            Cursor cur = db.rawQuery("Select sl_no from UploadDataforGps where Latitude IS NOT NULL and Longitude IS NOT NULL and userId=? ", whereArgs);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }
    public int getNumberOfPendingData2GPS(String userId) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};
            Cursor cur = db.rawQuery("Select sl_no from UploadDataforGps where userId=? ", whereArgs);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }

    public int getNumberOfPendingData3() {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {null, null};
            Cursor cur = db.rawQuery("Select * from myVoutcher", null);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }

    public long setDistrictDataLocalUserWise(ArrayList<District> distlist, String userid) {

        long c = -1;
        ArrayList<District> dist = distlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (dist != null) {
            try {
                for (int i = 0; i < dist.size(); i++) {
                    values.put("Code", dist.get(i).get_DistCode());
                    values.put("Name", dist.get(i).get_DistName());
                    values.put("userid", userid);
                    String[] param = {dist.get(i).get_DistCode()};
                    //long update = db.update("DistDetail", values, "Code = ?", param);
                    //  if (!(update > 0))
                    c = db.insert("DistDetailUserBy", null, values);
                }
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setBlockDataForDist(ArrayList<Block> blocklist, String distCode) {

        long c = -1;
        ArrayList<Block> block = blocklist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL("Delete from Block");
        if (block.size() > 0) {
            try {


                for (int i = 0; i < block.size(); i++) {

                    values.put("Code", block.get(i).getBlockCode());
                    values.put("Name", block.get(i).getBlockName());
                    values.put("District_Code", distCode);
                  /*  String[] param={block.get(i).getCode()};
                    long update = db.update("Block", values, "Code = ?", param);
                    if (!(update>0))*/
                    c = db.insert("Block", null, values);
                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setPanchayatDataLocal(ArrayList<PanchayatWeb> panchayatlist, String sectorCode) {

        long c = -1;
        ArrayList<PanchayatWeb> panchayat = panchayatlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL("Delete from Panchayat");
        if (panchayat.size() > 0) {
            try {


                for (int i = 0; i < panchayat.size(); i++) {

                    values.put("Code", panchayat.get(i).getCode());
                    values.put("slno", panchayat.get(i).getAWC_Code());
                    values.put("Name", panchayat.get(i).getValue());
                    values.put("Sector_Code", panchayat.get(i).getSectorCode());
                   /* String[] param={panchayat.get(i).getCode()};
                    long update = db.update("Panchayat", values, "Code = ?", param);
                    if (!(update>0))*/
                    c = db.insert("Panchayat", null, values);

                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }
    public long setPanchayatDataForDistBlock(ArrayList<PanchayatData> panchayatlist, String distcode, String blkcode) {

        long c = -1;
        //CREATE TABLE `Panchayat1` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DistCode` TEXT,
        // `BlockCode` TEXT, `PanchayatCode` TEXT, `PanchayatName` TEXT )
        ArrayList<PanchayatData> panchayat = panchayatlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL("Delete from Panchayat1");
        if (panchayat.size() > 0) {
            try {


                for (int i = 0; i < panchayat.size(); i++) {

                    values.put("PanchayatCode", panchayat.get(i).getPcode());
                    values.put("PanchayatName", panchayat.get(i).getPname());
                    values.put("DistCode", distcode);
                    values.put("BlockCode", blkcode);
                    // values.put("Sector_Code", panchayat.get(i).getSectorCode());
                   /* String[] param={panchayat.get(i).getCode()};
                    long update = db.update("Panchayat", values, "Code = ?", param);
                    if (!(update>0))*/
                    c = db.insert("Panchayat1", null, values);

                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public String getPanchayatName(String pcode, Context context) {
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String kname = "";

        String[] whereArgs = new String[]{pcode};
        Cursor c = db.rawQuery(
                "select * from Panchayat where Code=?",
                whereArgs);

        if (c.getCount() > 0) {
            c.moveToNext();
            kname = c.getString(c.getColumnIndex("Name"));

        }
        c.close();


        return kname;

    }


    public String getDistrictName(String dcode, Context context) {
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String name = "";

        String[] whereArgs = new String[]{dcode};
        Cursor c = db.rawQuery(
                "select * from District where Code=?",
                whereArgs);

        if (c.getCount() > 0) {
            c.moveToNext();
            name = c.getString(c.getColumnIndex("Name"));

        }
        c.close();
        return name;

    }


    public String getBlockName(String bcode, Context context) {
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String name = "";

        String[] whereArgs = new String[]{bcode};
        Cursor c = db.rawQuery(
                "select * from Block where Code=?",
                whereArgs);

        if (c.getCount() > 0) {
            c.moveToNext();
            name = c.getString(c.getColumnIndex("Name"));

        }
        c.close();
        return name;

    }


    public String getSpinnerValue(Context context, String fieldName, String code) {

        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String name = "";

        String[] whereArgs = new String[]{fieldName, code};
        Cursor c = db.rawQuery(
                "select Value from SpinnerMaster where Field=? and Code=?",
                whereArgs);

        if (c.getCount() > 0) {
            c.moveToNext();
            name = c.getString(c.getColumnIndex("Value"));

        }
        c.close();
        return name;
    }


    public long deleteCredential(Context context, String pid) {

        DataBaseHelper placeData = new DataBaseHelper(context);
        SQLiteDatabase db = placeData.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("Block_Code", "");
        values.put("BlockName", "");
        values.put("District_Code", "");
        values.put("DistrictName", "");
        values.put("Panchayat_Code", "");
        values.put("PanchayatName", "");
        values.put("WardName", "");
        values.put("Ward_Code", "");
        values.put("Fyear", "");
        values.put("FyearName", "");
        String[] param = {"1"};

        long update = db.update("Credential", values, "slno = ?", param);
        if (!(update > 0)) {
            update = db.insert("Credential", null, values);
        }
        db.close();
        return update;
    }



    public ArrayList<ward> getWardList(String Pan_Code) {
        ArrayList<ward> deptList = new ArrayList<ward>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { Pan_Code };

            Cursor cur = db
                    .rawQuery(
                            "SELECT * from Ward WHERE PanchayatCode= ?", params);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                ward dept = new ward();
                dept.setWardCode(cur.getString(cur.getColumnIndex("WardCode")));
                dept.setWardname(cur.getString(cur.getColumnIndex("WardName")));
                dept.setPanchayatCode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                dept.setAreaType(cur.getString(cur.getColumnIndex("AreaType")));

                deptList.add(dept);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return deptList;
    }

    public ArrayList<VillageListEntity> getVillageList(String Pan_Code) {
        ArrayList<VillageListEntity> deptList = new ArrayList<VillageListEntity>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { Pan_Code };

            Cursor cur = db
                    .rawQuery(
                            "SELECT * from VillageList WHERE PanchayatCode= ?", params);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                VillageListEntity dept = new VillageListEntity();
                dept.setVillCode(cur.getString(cur.getColumnIndex("VillageCode")));
                dept.setVillName(cur.getString(cur.getColumnIndex("VillageName")));
                dept.setPanchayatCode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                dept.setBlockCode(cur.getString(cur.getColumnIndex("BLOCKCODE")));

                deptList.add(dept);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return deptList;
    }

    public ArrayList<PanchayatData> getPanchayt(String blockCode) {
        ArrayList<PanchayatData> panchayatList = new ArrayList<PanchayatData>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { blockCode };

            Cursor cur = db
                    .rawQuery(
                            "SELECT PanchayatCode,PanchayatName,DistrictCode,BlockCode,PACName from Panchayat WHERE BlockCode = ? ORDER BY PanchayatName",
                            params);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                PanchayatData panchayat = new PanchayatData();
                panchayat.setPcode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                panchayat.setPname(cur.getString(cur.getColumnIndex("PanchayatName")));
                panchayat.setBcode(cur.getString(cur.getColumnIndex("BlockCode")));
                panchayat.setDcode(cur.getString(cur.getColumnIndex("DistrictCode")));
                panchayat.setAreaType(cur.getString(cur.getColumnIndex("PACName")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return panchayatList;
    }


    public ArrayList<PanchayatData> getPanchaytAreawise(String blockCode, String areaType) {
        ArrayList<PanchayatData> panchayatList = new ArrayList<PanchayatData>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { blockCode, areaType };

            Cursor cur = db
                    .rawQuery(
                            "SELECT PanchayatCode,PanchayatName,DistrictCode,BlockCode,PACName from Panchayat WHERE BlockCode = ? AND PACName = ? ORDER BY PanchayatName",
                            params);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                PanchayatData panchayat = new PanchayatData();
                panchayat.setPcode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                panchayat.setPname(cur.getString(cur.getColumnIndex("PanchayatName")));
                panchayat.setBcode(cur.getString(cur.getColumnIndex("BlockCode")));
                panchayat.setDcode(cur.getString(cur.getColumnIndex("DistrictCode")));
                panchayat.setAreaType(cur.getString(cur.getColumnIndex("PACName")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return panchayatList;
    }


    public ArrayList<District> getDistrict() {
        ArrayList<District> districtList = new ArrayList<District>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db
                    .rawQuery(
                            "SELECT DistCode,DistName,DistCode3,Zone,Circle from DistrictMwrd ORDER BY DistName",
                            null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                District district = new District();
                district.set_DistCode(cur.getString(cur.getColumnIndex("DistCode")));
                district.set_DistName(cur.getString(cur.getColumnIndex("DistName")));
                district.setDistCode3(cur.getString(cur.getColumnIndex("DistCode3")));
                district.setZone(cur.getString(cur.getColumnIndex("Zone")));
                district.setCircle(cur.getString(cur.getColumnIndex("Circle")));

                districtList.add(district);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return districtList;
    }

    public ArrayList<District> getDistrictUserBy() {
        //CREATE TABLE "DistDetail" ( `Code` TEXT NOT NULL, `Name` TEXT, `slno`
        // INTEGER, PRIMARY KEY(`Code`) )
        ArrayList<District> districtList = new ArrayList<District>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db
                    .rawQuery(
                            "SELECT Code,Name from DistDetailUserBy ORDER BY Name",
                            null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                District district = new District();
                district.set_DistCode(cur.getString(cur
                        .getColumnIndex("Code")));
                district.set_DistName(cur.getString(cur
                        .getColumnIndex("Name")));

                districtList.add(district);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception

        }
        return districtList;

    }

    public ArrayList<Block> getBlock(String distCode) {

        ArrayList<Block> blockList = new ArrayList<Block>();
//CREATE TABLE `Block` ( `slno` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
// `District_Code` TEXT, `Code` TEXT, `Name` TEXT )
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { distCode };
            Cursor cur = db
                    .rawQuery(
                            "SELECT BlockCode,DistCode,BlockName from Blocks WHERE DistCode = ? ORDER BY BlockName ",
                            params);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                Block block = new Block();
                block.setBlockCode(cur.getString(cur
                        .getColumnIndex("BlockCode")));
                block.setBlockName(cur.getString(cur
                        .getColumnIndex("BlockName")));
                block.setDistCode(cur.getString(cur
                        .getColumnIndex("DistCode")));

                blockList.add(block);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return blockList;

    }
    public ArrayList<PanchayatData> getPanchayatLocal(String blkId) {
        //CREATE TABLE `Panchayat1` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DistCode` TEXT,
        // `BlockCode` TEXT, `PanchayatCode` TEXT, `PanchayatName` TEXT )
        ArrayList<PanchayatData> pdetail = new ArrayList<PanchayatData>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM  Panchayat1 where BlockCode='" + blkId + "' order by PanchayatName", null);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                PanchayatData panchayat = new PanchayatData();
                panchayat.setPcode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                panchayat.setPname((cur.getString(cur.getColumnIndex("PanchayatName"))));
                pdetail.add(panchayat);
            }
            cur.close();
            db.close();
        }
        catch (Exception e) {
        }
        return pdetail;
    }

    public String getPostWhereConditionForStudentListForAttendance1(String finyr, String schemeid, String fund_id, String scheme_type, String distid) {

        String subWhere=" ";

        if(!finyr.equalsIgnoreCase("0"))
        {
            subWhere += "AND FINANCIAL_YEAR='"+finyr+"'";
        }
        if(!schemeid.equalsIgnoreCase(""))
        {
            subWhere += " AND SCHEME_ID='"+ schemeid +"'";
        }
        if(!fund_id.equalsIgnoreCase("0"))
        {
            subWhere += " AND Fund_Type='"+ fund_id  +"'";
        }
        if(!scheme_type.equalsIgnoreCase("0"))
        {
            subWhere += " AND TYPE_OF_SCHEME='"+ scheme_type  +"'";
        }
        if(!distid.equalsIgnoreCase(""))
        {
            subWhere += " AND District='"+ distid  +"'";
        }

        Log.e("SUBQUERY",subWhere);
        return subWhere;
    }
}