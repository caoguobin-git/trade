/***********************************************
 * File Name: CalcServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 06 2019 15:22
 ***********************************************/

package com.duochuang.serviceImpl;

import com.duochuang.common.anno.CalcAnno;
import com.duochuang.service.CalcService;
import org.springframework.stereotype.Service;

@Service
public class CalcServiceImpl implements CalcService {
    @Override
    @CalcAnno
    public String add(String a, String b) {
        int x= Integer.parseInt(a);
        int y=Integer.parseInt(b);
        return String.valueOf(x+y);
    }
}
