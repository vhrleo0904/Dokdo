package kr.hs.dgsw.dokdo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "onCreate");
        String sql = "create table tab ( id integer primary key autoincrement, title text, content text /*, tag text*/)";
        db.execSQL(sql);
        ContentValues value = new ContentValues();
        db.beginTransaction();
        /*value.put("title", "title");
        value.put("content", "content");
        db.insert("tab", null, value);*/
        try {
            value.put("title", "독도에 대한 소개(Introduce Dokdo)");
            value.put("content", "독도는 동경 131º, 북위 37º에 위치해있으며 면적은 187,554㎡입니다.\n"
                                    + "주소는 경상북도 울릉군 울릉읍 독도리이며,\n"
                                    + "평균기온은 13도, 평균 강수량은 1300입니다.");
            db.insert("tab", null, value);

            value.put("title", "독도로 가는 방법(Way to Dokdo)");
            value.put("content", "독도에 입도하기 위해선 먼저 입도 신청을 해야합니다.\n\n"
                                    + "일반 관람의 경우 1일 내 입도가능인원 선착순 접수이며 "
                                    + "행사, 촬영, 숙박 등의 특수목적의 경우 14일 이전에 "
                                    + "입도 허가 신청서를 작성하여야 입도할 수 있습니다.\n\n"
                                    + "대저해운에서 포항~울릉도~독도 구간의 배를 1일 2회 운항하며,"
                                    + "풍랑 등의 기상특보가 발생시 결항될 수 있습니다.");
            db.insert("tab", null, value);

            value.put("title", "독도의 자연경관(Natural scenery of Dokdo)");
            value.put("content", "독도 섬 주변의 바다에 다양한 해양생물이 서식하고 있으며 "
                                    + "섬 일대의 자연환경을 보존하기 위해 이 섬을 천연기념물로 지정하여 보호하고 있습니다."/*
                                    + "독도에서 관찰된 조류는 괭이갈매기 등 126종, 곤충은 7목 26과 37종, 인근 해양엔 파랑돔, "
                                    + "넙치, 미역치, 가막베도라치 등의 다양한 어류와 무척추동물 26종이 살고 있다고 발표했습니다.\n\n"
                                    + "또한 독도의 식물은 29과 48속 49종, 1아종 3변종 총 53종류로 조사되었으며, 이중 독도 사철나무는 "
                                    + "독도에서 현존하는 수목 중 가장 오래된 나무로 국토의 동쪽 끝에서 독도를 100년 이상 지켜왔다는 "
                                    + "여러 가치로 인해 대한민국 천연기념물 제 538호로 지정되었습니다."*/);
            db.insert("tab", null, value);

            value.put("title", "독도의 역사(History of Dokdo)");
            value.put("content", "독도는 약 460~250만여년 전에 생성되었습니다.\n 한 섬이었으나 동해의 해수면 상승으로 인해 "
                                    + "2개의 봉우리만 솟아올라 2개의 섬으로 나뉘어졌습니다.");
            db.insert("tab", null, value);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("DB", e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DB", "onUpgrade");
        String sql = "drop table tab";
        db.execSQL(sql);
        onCreate(db);
    }

    // select *
    public ArrayList<TabBean> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("tab", null, null, null, null, null, "id asc");
        ArrayList<TabBean> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            TabBean tab = new TabBean();
            tab.setId(cursor.getInt(cursor.getColumnIndex("id")));
            tab.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            tab.setContent(cursor.getString(cursor.getColumnIndex("content")));
            //tab.setTag(cursor.getString(cursor.getColumnIndex("tag")));
            result.add(tab);
        }
        return result;
    }

    // select search from tab;
    public ArrayList<TabBean> get(String search) {
        Log.d("DB", "get : " + search);

        ArrayList<TabBean> result = getAll();

        for(int i=0; i<result.size(); i++) {
            if(!(result.get(i).getTitle().contains(search) || result.get(i).getContent().contains(search))) {
                result.remove(i--);
            }
        }

        return result;
    }
}
