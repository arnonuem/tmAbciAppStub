package net.arnonuem.tmstub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jtendermint.jabci.api.IBeginBlock;
import com.github.jtendermint.jabci.api.ICheckTx;
import com.github.jtendermint.jabci.api.ICommit;
import com.github.jtendermint.jabci.api.IDeliverTx;
import com.github.jtendermint.jabci.api.IEcho;
import com.github.jtendermint.jabci.api.IEndBlock;
import com.github.jtendermint.jabci.api.IFlush;
import com.github.jtendermint.jabci.api.IInfo;
import com.github.jtendermint.jabci.api.IInitChain;
import com.github.jtendermint.jabci.api.IQuery;
import com.github.jtendermint.jabci.api.ISetOption;
import com.github.jtendermint.jabci.types.Types.CodeType;
import com.github.jtendermint.jabci.types.Types.RequestBeginBlock;
import com.github.jtendermint.jabci.types.Types.RequestCheckTx;
import com.github.jtendermint.jabci.types.Types.RequestCommit;
import com.github.jtendermint.jabci.types.Types.RequestDeliverTx;
import com.github.jtendermint.jabci.types.Types.RequestEcho;
import com.github.jtendermint.jabci.types.Types.RequestEndBlock;
import com.github.jtendermint.jabci.types.Types.RequestFlush;
import com.github.jtendermint.jabci.types.Types.RequestInfo;
import com.github.jtendermint.jabci.types.Types.RequestInitChain;
import com.github.jtendermint.jabci.types.Types.RequestQuery;
import com.github.jtendermint.jabci.types.Types.RequestSetOption;
import com.github.jtendermint.jabci.types.Types.ResponseBeginBlock;
import com.github.jtendermint.jabci.types.Types.ResponseCheckTx;
import com.github.jtendermint.jabci.types.Types.ResponseCommit;
import com.github.jtendermint.jabci.types.Types.ResponseDeliverTx;
import com.github.jtendermint.jabci.types.Types.ResponseEcho;
import com.github.jtendermint.jabci.types.Types.ResponseEndBlock;
import com.github.jtendermint.jabci.types.Types.ResponseFlush;
import com.github.jtendermint.jabci.types.Types.ResponseInfo;
import com.github.jtendermint.jabci.types.Types.ResponseInitChain;
import com.github.jtendermint.jabci.types.Types.ResponseQuery;
import com.github.jtendermint.jabci.types.Types.ResponseSetOption;

//TODO workaround since there is an issue with ABCIAPI
public class TSockListener /*implements ABCIAPI*/implements IDeliverTx, IBeginBlock, ICheckTx, ICommit, IEndBlock, IFlush, IInfo, IInitChain, IQuery, ISetOption, IEcho {

	private static final Logger log = LoggerFactory.getLogger( TSockListener.class );
	
    @Override
    public ResponseDeliverTx receivedDeliverTx(RequestDeliverTx req) {
        log.debug("ResponseDeliverTx DefaultFallbackListener");
        return ResponseDeliverTx.newBuilder().setCode(CodeType.OK).build();
    }

    @Override
    public ResponseFlush requestFlush(RequestFlush reqfl) {
        log.debug("ResponseFlush DefaultFallbackListener");
        return ResponseFlush.newBuilder().build();
    }

    @Override
    public ResponseCommit requestCommit(RequestCommit requestCommit) {
        log.debug("ResponseCommit DefaultFallbackListener");
        return ResponseCommit.newBuilder().setCode(CodeType.OK).build();
    }

    @Override
    public ResponseBeginBlock requestBeginBlock(RequestBeginBlock req) {
        log.debug("ResponseBeginBlock DefaultFallbackListener");
        return ResponseBeginBlock.newBuilder().build();
    }

    @Override
    public ResponseCheckTx requestCheckTx(RequestCheckTx req) {
        log.debug("ResponseCheckTx DefaultFallbackListener");
        return ResponseCheckTx.newBuilder().setCode(CodeType.OK).build();
    }

    @Override
    public ResponseEndBlock requestEndBlock(RequestEndBlock req) {
        log.debug("ResponseEndBlock DefaultFallbackListener");
        return ResponseEndBlock.newBuilder().build();
    }

    @Override
    public ResponseInfo requestInfo(RequestInfo req) {
        log.debug("ResponseInfo DefaultFallbackListener");
        return ResponseInfo.newBuilder().setData("NO_INFO").build();
    }

    @Override
    public ResponseInitChain requestInitChain(RequestInitChain req) {
        log.debug("ResponseInitChain DefaultFallbackListener");
        return ResponseInitChain.newBuilder().build();
    }

    @Override
    public ResponseQuery requestQuery(RequestQuery req) {
        log.debug("ResponseQuery DefaultFallbackListener");
        return ResponseQuery.newBuilder().setCode(CodeType.OK).build();
    }

    @Override
    public ResponseSetOption requestSetOption(RequestSetOption req) {
        log.debug("ResponseSetOption DefaultFallbackListener");
        return ResponseSetOption.newBuilder().build();
    }

    @Override
    public ResponseEcho requestEcho(RequestEcho req) {
        log.debug("ResponseEcho DefaultFallbackListener");
        return ResponseEcho.newBuilder().setMessage("NOECHO").build();
    }
}
