package cn.yangwanhao.multipledatasource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import cn.yangwanhao.multipledatasource.mapper.base.DictItemMapper;
import cn.yangwanhao.multipledatasource.mapper.base.DictMapper;
import cn.yangwanhao.multipledatasource.model.base.Dict;
import cn.yangwanhao.multipledatasource.model.base.DictItem;
import cn.yangwanhao.multipledatasource.request.AddDictRequest;
import cn.yangwanhao.multipledatasource.service.IDictService;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/10/8 15:55
 */
@Service
public class DictServiceImpl implements IDictService {

    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private DictItemMapper dictItemMapper;
    @Autowired
    @Qualifier("baseTransactionTemplate")
    private TransactionTemplate transactionTemplate;

    @Override
    // @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class, value = "baseTransactionManager")
    public void addDict(AddDictRequest request) {
        transactionTemplate.execute(transactionTemplate -> {
            insertDict(request);
            insertDictItem(request);
            return true;
        });
        int a = 1 / 0;
    }

    private void  insertDict(AddDictRequest request) {
        Dict dict = new Dict();
        dict.setDictKey(request.getDictKey());
        dict.setDictName(request.getDictName());
        dict.setDictDesc(request.getDictDesc());
        dict.setTenantId(request.getTenantId());
        dictMapper.insert(dict);
    }
    private void  insertDictItem(AddDictRequest request) {
        DictItem dictItem = new DictItem();
        dictItem.setDictKey(request.getDictKey());
        dictItem.setItemKey(request.getItemKey());
        dictItem.setItemName(request.getItemName());
        dictItem.setTenantId(request.getTenantId());
        dictItemMapper.insert(dictItem);
    }
}
