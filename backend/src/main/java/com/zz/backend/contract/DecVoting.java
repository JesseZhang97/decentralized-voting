package com.zz.backend.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class DecVoting extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060006100246001600160e01b0361008016565b600080546001600160a01b0319166001600160a01b0383169081178255604051929350917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908290a3506000805460ff60a01b19169055610084565b3390565b610d93806100936000396000f3fe608060405234801561001057600080fd5b506004361061010b5760003560e01c80638da5cb5b116100a2578063a9ff6df111610071578063a9ff6df1146101db578063b21a64da146101ee578063c19d93fb146101f6578063dff7d2c71461020b578063f2fde38b146102135761010b565b80638da5cb5b146101bb5780639af5c09d146101c35780639bcc3361146101cb5780639c84bb27146101d35761010b565b806338db6dd3116100de57806338db6dd3146101765780635d55f2de14610189578063715018a6146101a957806386f5114e146101b35761010b565b80631ea736e0146101105780632019a6081461012e5780632c3d69e3146101365780633477ee2e14610156575b600080fd5b610118610226565b6040516101259190610d0d565b60405180910390f35b61011861022c565b610149610144366004610b0b565b610232565b6040516101259190610bf6565b610169610164366004610bca565b61038a565b6040516101259190610c15565b610149610184366004610ae9565b610430565b61019c610197366004610bca565b61050b565b6040516101259190610be2565b6101b1610532565b005b6101696105b1565b61019c61060b565b61011861061b565b610118610621565b610118610627565b6101496101e9366004610ae9565b61062d565b610149610642565b6101fe6106e0565b6040516101259190610c01565b6101186106f0565b6101b1610221366004610ae9565b6106f6565b60055481565b60045481565b60008080600054600160a01b900460ff16600481111561024e57fe5b146102745760405162461bcd60e51b815260040161026b90610cae565b60405180910390fd5b61027c610737565b6000546001600160a01b039081169116146102a95760405162461bcd60e51b815260040161026b90610cd8565b60028510156102bb576000915061037c565b60028610156102cd576000915061037c565b428a10156102de576000915061037c565b888a11156102ef576000915061037c565b878911806102fc57508688115b1561030a576000915061037c565b8a5161031d9060019060208e01906107bc565b5060028a905560038990556004889055600587905560068690556008859055835161034f906007906020870190610836565b508251610363906009906020860190610897565b506000805460ff60a01b1916600160a01b179055600191505b509998505050505050505050565b6009818154811061039757fe5b600091825260209182902001805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152935090918301828280156104285780601f106103fd57610100808354040283529160200191610428565b820191906000526020600020905b81548152906001019060200180831161040b57829003601f168201915b505050505081565b6000600180600054600160a01b900460ff16600481111561044d57fe5b1461046a5760405162461bcd60e51b815260040161026b90610cae565b610472610737565b6000546001600160a01b0390811691161461049f5760405162461bcd60e51b815260040161026b90610cd8565b6002544210156104b25760009150610505565b6001600160a01b0383166000908152600a602052604090205460ff16156104dc5760009150610505565b6001600160a01b0383166000908152600a60205260409020805460ff1916600190811790915591505b50919050565b6007818154811061051857fe5b6000918252602090912001546001600160a01b0316905081565b61053a610737565b6000546001600160a01b039081169116146105675760405162461bcd60e51b815260040161026b90610cd8565b600080546040516001600160a01b03909116907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908390a3600080546001600160a01b0319169055565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156104285780601f106103fd57610100808354040283529160200191610428565b6000546001600160a01b03165b90565b60025481565b60065481565b60085481565b600a6020526000908152604090205460ff1681565b6000600180600054600160a01b900460ff16600481111561065f57fe5b1461067c5760405162461bcd60e51b815260040161026b90610cae565b610684610737565b6000546001600160a01b039081169116146106b15760405162461bcd60e51b815260040161026b90610cd8565b4260035414156106d7576000805460ff60a01b1916600160a11b179055600191506106dc565b600091505b5090565b600054600160a01b900460ff1681565b60035481565b6106fe610737565b6000546001600160a01b0390811691161461072b5760405162461bcd60e51b815260040161026b90610cd8565b6107348161073b565b50565b3390565b6001600160a01b0381166107615760405162461bcd60e51b815260040161026b90610c68565b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b0392909216919091179055565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106107fd57805160ff191683800117855561082a565b8280016001018555821561082a579182015b8281111561082a57825182559160200191906001019061080f565b506106dc9291506108f0565b82805482825590600052602060002090810192821561088b579160200282015b8281111561088b57825182546001600160a01b0319166001600160a01b03909116178255602090920191600190910190610856565b506106dc92915061090a565b8280548282559060005260206000209081019282156108e4579160200282015b828111156108e457825180516108d49184916020909101906107bc565b50916020019190600101906108b7565b506106dc92915061092e565b61061891905b808211156106dc57600081556001016108f6565b61061891905b808211156106dc5780546001600160a01b0319168155600101610910565b61061891905b808211156106dc5760006109488282610951565b50600101610934565b50805460018160011615610100020316600290046000825580601f106109775750610734565b601f01602090049060005260206000209081019061073491906108f0565b80356001600160a01b03811681146109ac57600080fd5b92915050565b600082601f8301126109c2578081fd5b81356109d56109d082610d3d565b610d16565b8181529150602080830190848101818402860182018710156109f657600080fd5b60005b84811015610a1d57610a0b8883610995565b845292820192908201906001016109f9565b505050505092915050565b600082601f830112610a38578081fd5b8135610a466109d082610d3d565b818152915060208083019084810160005b84811015610a1d57610a6e888484358a0101610a80565b84529282019290820190600101610a57565b600082601f830112610a90578081fd5b813567ffffffffffffffff811115610aa6578182fd5b610ab9601f8201601f1916602001610d16565b9150808252836020828501011115610ad057600080fd5b8060208401602084013760009082016020015292915050565b600060208284031215610afa578081fd5b610b048383610995565b9392505050565b60008060008060008060008060006101208a8c031215610b29578485fd5b893567ffffffffffffffff80821115610b40578687fd5b610b4c8d838e01610a80565b9a5060208c0135995060408c0135985060608c0135975060808c0135965060a08c0135955060c08c0135945060e08c0135915080821115610b8b578384fd5b610b978d838e016109b2565b93506101008c0135915080821115610bad578283fd5b50610bba8c828d01610a28565b9150509295985092959850929598565b600060208284031215610bdb578081fd5b5035919050565b6001600160a01b0391909116815260200190565b901515815260200190565b6020810160058310610c0f57fe5b91905290565b6000602080835283518082850152825b81811015610c4157858101830151858201604001528201610c25565b81811115610c525783604083870101525b50601f01601f1916929092016040019392505050565b60208082526026908201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160408201526564647265737360d01b606082015260800190565b60208082526010908201526f766f74652073746167652077726f6e6760801b604082015260600190565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b90815260200190565b60405181810167ffffffffffffffff81118282101715610d3557600080fd5b604052919050565b600067ffffffffffffffff821115610d53578081fd5b506020908102019056fea26469706673582212200e61025f77f2ba8b655b1d71c05abf464820f623c27f0f2167ba600a861df95d64736f6c63430006020033";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_ENDREGISTRATIONPHASE = "endRegistrationPhase";

    public static final String FUNC_FINISHSETUP = "finishSetUp";

    public static final String FUNC_NUMBEROFCANDIDATES = "numberOfCandidates";

    public static final String FUNC_NUMBEROFVOTERS = "numberOfVoters";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REGISTERVOTER = "registerVoter";

    public static final String FUNC_REGISTEREDVOTERS = "registeredVoters";

    public static final String FUNC_REGISTRATIONENDTIME = "registrationEndTime";

    public static final String FUNC_REGISTRATIONSTARTTIME = "registrationStartTime";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_STATE = "state";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_VOTENAME = "voteName";

    public static final String FUNC_VOTERADDRESS = "voterAddress";

    public static final String FUNC_VOTINGENDTIME = "votingEndTime";

    public static final String FUNC_VOTINGSTARTTIME = "votingStartTime";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected DecVoting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DecVoting(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DecVoting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DecVoting(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<String> candidates(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> endRegistrationPhase() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENDREGISTRATIONPHASE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> finishSetUp(String _voteName, BigInteger _registrationStartTime, BigInteger _registrationEndTime, BigInteger _votingStartTime, BigInteger _votingEndTime, BigInteger _numberOfVoters, BigInteger _numberOfCandidates, List<String> _voterAddress, List<String> _candidates) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FINISHSETUP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_voteName), 
                new org.web3j.abi.datatypes.generated.Uint256(_registrationStartTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_registrationEndTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_votingStartTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_votingEndTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_numberOfVoters), 
                new org.web3j.abi.datatypes.generated.Uint256(_numberOfCandidates), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(_voterAddress, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_candidates, org.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> numberOfCandidates() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NUMBEROFCANDIDATES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> numberOfVoters() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NUMBEROFVOTERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> registerVoter(String publickey) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERVOTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, publickey)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> registeredVoters(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REGISTEREDVOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> registrationEndTime() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REGISTRATIONENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> registrationStartTime() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REGISTRATIONSTARTTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> state() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> voteName() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOTENAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> voterAddress(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOTERADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> votingEndTime() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOTINGENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> votingStartTime() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOTINGSTARTTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static DecVoting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DecVoting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DecVoting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DecVoting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DecVoting load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DecVoting(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DecVoting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DecVoting(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DecVoting> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DecVoting.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<DecVoting> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DecVoting.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DecVoting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DecVoting.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DecVoting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DecVoting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
