package com.wan.synthesize.web.action;

import com.wan.synthesize.baseenum.ReturenCodeEnum;
import com.wan.synthesize.common.ReturnResult;
import com.wan.synthesize.domain.Bill;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wzx on 2017/2/9.
 */
@RequestMapping("/bill")
@Controller
public class BillController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping("/add")
    @ResponseBody
    public ReturnResult add(Bill bill){
        ReturnResult result = new ReturnResult();
        try {
            String validate = bill.validate();
            if(!"".equals(validate)){
                result.setSuccess(false);
                result.setMessage(validate);
                result.setCode(ReturenCodeEnum.DATA_IS_NULL.getCode());
                return result;
            }

        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(ReturenCodeEnum.SYSTEM_ERROR.getDesc());
            result.setCode(ReturenCodeEnum.SYSTEM_ERROR.getCode());
            logger.error(e.getMessage(),e);
        }
        return result;
    }
}
