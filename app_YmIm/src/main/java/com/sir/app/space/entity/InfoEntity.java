package com.sir.app.space.entity;

import com.amap.api.maps.model.LatLng;
import com.sir.app.space.R;

import java.util.List;


/**
 * Created by zhuyinan on 2016/3/30.
 */

public class InfoEntity {
    public int id = 1001;
    public String head = "http://cdn.duitang.com/uploads/item/201207/24/20120724123200_x85tj.jpeg";
    public String userName = "YouTube上的搬运工";
    public String creatTime = "2016-03-12";
    public int icon = R.mipmap.ic_launcher;
    public String titile = "标题";
    public String content = "内容";
    public LatLng latLng1 = new LatLng(22.538588, 114.085465);
    public LatLng latLng2 = new LatLng(22.537588, 114.185465);
    public LatLng latLng3 = new LatLng(22.546588, 114.055465);
    public LatLng latLng4 = new LatLng(22.535588, 114.065465);
    public LatLng latLng5 = new LatLng(22.554588, 114.075465);
    public LatLng latLng6 = new LatLng(22.573588, 114.085465);


    public String[] Images = {"http://img4.imgtn.bdimg.com/it/u=2983836196,3749268742&fm=21&gp=0.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=e1f8e37e8a82b9013dadc33b438da97e/5554e8ea15ce36d3fb3d4b693af33a87e850b18b.jpg",
            "http://imgsrc.baidu.com/forum/w=580/sign=1f046afc9f25bc312b5d01906ede8de7/90d72912b31bb051cae80c1a307adab44bede050.jpg",
            "http://imgsrc.baidu.com/forum/w=580/sign=4dbbbff13c87e9504217f3642039531b/fa5dec039245d6882f840296a2c27d1ed31b249a.jpg"};
}
