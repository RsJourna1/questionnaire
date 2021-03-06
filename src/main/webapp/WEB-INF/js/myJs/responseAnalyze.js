/**
 * questionnaire
 * Created by 郑晓辉 on 2017/4/9.
 * Contact : zxh980278090@gmail.com
 * 需要提前引入jq、layer js
 */

function accessServerByJson(url, jsonData) {
    $.ajax({
        url: url,
        contentType: "application/json;charset=utf-8",
        type: 'post',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            if (200 === data.code) {
                successResultLayer("操作成功！");
            }
            dealGlobalError(data);
        },
        error: function (data) {
            alert(data);
        }
    });
}
/**
 * code === 200 layer层展示
 * @param successMsg
 */
function successResultLayer(successMsg) {
    layer.msg(successMsg, {
        icon: 1,
        time: 3000,
        shade: 0.5,
        closeBtn: 1
    });
}
/**
 * 全局错误通用处理
 * @param responsePkt
 */
function dealGlobalError(responsePkt) {
    /*数据库相关错误*/
    if (responsePkt.code === -5) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === -4) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === -3) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === -2) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === -1) {
        layerErrorMessage(responsePkt);
    }
    /*服务器以及网络错误*/
    if (responsePkt.code === 500) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === 404) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === 400) {
        layerErrorMessage(responsePkt);
    }
    /*用户相关错误*/
    if (responsePkt.code === 11) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === 12) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === 13) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === 14) {
        layerErrorMessage(responsePkt);
    }
    /*校验失败信息处理*/
    if (responsePkt.code === -1000) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === -1010) {
        layerErrorMessage(responsePkt);
    }
    if (responsePkt.code === -1020) {
        layerErrorMessage(responsePkt);
    }
}
/*错误信息统一layer处理*/
function layerErrorMessage(responsePkt) {
    layer.msg(responsePkt.message, {
        icon: 5,
        shade: 0.5,
        btn: ['知道了'],
        time : 0
    });
}