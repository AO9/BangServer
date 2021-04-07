package com.gto.bang.dao;


import com.gto.bang.model.Article;
import com.gto.bang.model.ArticleWithBLOBs;
import com.gto.bang.model.Operation;
import com.gto.bang.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperationMapper {

    // 获取全站PV数据 20210404
    List<Operation> getPV(@Param("operationType") String operationType);

    // 获取立即报名的信息
    List<Operation> selectStutdents();

    int deleteByPrimaryKey(Integer id);

    int insert(Operation record);

    int insertSelective(Operation record);

    Operation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Operation record);

    int updateByPrimaryKey(Operation record);
}