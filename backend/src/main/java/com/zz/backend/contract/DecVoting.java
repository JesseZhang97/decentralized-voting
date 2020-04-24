package com.zz.backend.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
    public static final String BINARY = "60806040526103e760015534801561001657600080fd5b506000805460ff19168155610dab90819061003190396000f3fe608060405234801561001057600080fd5b506004361061012c5760003560e01c80639cc2d993116100ad578063b767eb9811610071578063b767eb9814610228578063c19d93fb1461023b578063cbff75cb14610250578063d299346b14610263578063dff7d2c71461026b5761012c565b80639cc2d993146101ea578063a300e224146101fd578063a9ff6df114610205578063b21a64da14610218578063b43d5f15146102205761012c565b806364f62cc0116100f457806364f62cc0146101b757806381929bac146101bf57806386f5114e146101d257806398f0b34c146101da5780639af5c09d146101e25761012c565b80631ea736e0146101315780632019a6081461014f5780633477ee2e1461015757806338db6dd3146101775780635d55f2de14610197575b600080fd5b610139610273565b6040516101469190610d25565b60405180910390f35b610139610279565b61016a610165366004610bed565b61027f565b6040516101469190610ca8565b61018a610185366004610a84565b610325565b6040516101469190610c89565b6101aa6101a5366004610bed565b6103ba565b6040516101469190610c75565b6101396103e1565b6101396101cd366004610aa6565b6103e8565b61016a610405565b61013961045d565b610139610463565b61018a6101f8366004610aa6565b610469565b61018a61054f565b61018a610213366004610a84565b6105bb565b61018a6105d0565b61018a610618565b61018a610236366004610ae1565b6106f1565b610243610790565b6040516101469190610c94565b61018a61025e366004610a84565b610799565b6101396107ae565b6101396107b4565b60065481565b60055481565b6008818154811061028c57fe5b600091825260209182902001805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529350909183018282801561031d5780601f106102f25761010080835404028352916020019161031d565b820191906000526020600020905b81548152906001019060200180831161030057829003601f168201915b505050505081565b600060018060005460ff16600481111561033b57fe5b146103615760405162461bcd60e51b815260040161035890610cfb565b60405180910390fd5b6001600160a01b03831660009081526009602052604090205460ff161561038b57600091506103b4565b6001600160a01b0383166000908152600960205260409020805460ff1916600190811790915591505b50919050565b600781815481106103c757fe5b6000918252602090912001546001600160a01b0316905081565b6008545b90565b8051602081830181018051600c8252928201919093012091525481565b6002805460408051602060018416156101000260001901909316849004601f8101849004840282018401909252818152929183018282801561031d5780601f106102f25761010080835404028352916020019161031d565b60075490565b60035481565b600060028060005460ff16600481111561047f57fe5b1461049c5760405162461bcd60e51b815260040161035890610cfb565b3360009081526009602052604090205460ff166104c05760018055600091506103b4565b336000908152600b602052604090205460ff16156104e6576002600155600091506103b4565b600a80546001810182556000919091528351610529917fc65a7bb8d6351c1cf70c95a316cc6a92839c986682d98bc35f958f4883f9d2a8019060208601906107ba565b50336000908152600b60205260409020805460ff19166001908117909155915050919050565b600060028060005460ff16600481111561056557fe5b146105825760405162461bcd60e51b815260040161035890610cfb565b6000805460ff191660039081179091555b60005460ff1660048111156105a457fe5b146105b257600091506105b7565b600191505b5090565b60096020526000908152604090205460ff1681565b600060018060005460ff1660048111156105e657fe5b146106035760405162461bcd60e51b815260040161035890610cfb565b6000805460ff19166002908117909155610593565b600060038060005460ff16600481111561062e57fe5b1461064b5760405162461bcd60e51b815260040161035890610cfb565b60005b600854811015610698576000600c6008838154811061066957fe5b906000526020600020016040516106809190610c05565b9081526040519081900360200190205560010161064e565b5060005b600a548110156106ec576001600c600a83815481106106b757fe5b906000526020600020016040516106ce9190610c05565b9081526040519081900360200190208054909101905560010161069c565b505090565b6000808060005460ff16600481111561070657fe5b146107235760405162461bcd60e51b815260040161035890610cfb565b88516107369060029060208c01906107ba565b506003889055600487905560058690556006859055835161075e906007906020870190610834565b508251610772906008906020860190610895565b50506000805460ff1916600190811790915598975050505050505050565b60005460ff1681565b600b6020526000908152604090205460ff1681565b60015481565b60045481565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106107fb57805160ff1916838001178555610828565b82800160010185558215610828579182015b8281111561082857825182559160200191906001019061080d565b506105b79291506108ee565b828054828255906000526020600020908101928215610889579160200282015b8281111561088957825182546001600160a01b0319166001600160a01b03909116178255602090920191600190910190610854565b506105b7929150610908565b8280548282559060005260206000209081019282156108e2579160200282015b828111156108e257825180516108d29184916020909101906107ba565b50916020019190600101906108b5565b506105b792915061092c565b6103e591905b808211156105b757600081556001016108f4565b6103e591905b808211156105b75780546001600160a01b031916815560010161090e565b6103e591905b808211156105b7576000610946828261094f565b50600101610932565b50805460018160011615610100020316600290046000825580601f106109755750610993565b601f01602090049060005260206000209081019061099391906108ee565b50565b80356001600160a01b03811681146109ad57600080fd5b92915050565b600082601f8301126109c3578081fd5b81356109d66109d182610d55565b610d2e565b818152915060208083019084810160005b84811015610a10576109fe888484358a0101610a1b565b845292820192908201906001016109e7565b505050505092915050565b600082601f830112610a2b578081fd5b813567ffffffffffffffff811115610a41578182fd5b610a54601f8201601f1916602001610d2e565b9150808252836020828501011115610a6b57600080fd5b8060208401602084013760009082016020015292915050565b600060208284031215610a95578081fd5b610a9f8383610996565b9392505050565b600060208284031215610ab7578081fd5b813567ffffffffffffffff811115610acd578182fd5b610ad984828501610a1b565b949350505050565b600080600080600080600060e0888a031215610afb578283fd5b67ffffffffffffffff8089351115610b11578384fd5b610b1e8a8a358b01610a1b565b97506020890135965060408901359550606089013594506080890135935060a089013581811115610b4d578384fd5b808a018b601f820112610b5e578485fd5b80359150610b6e6109d183610d55565b80838252602082019150602083018e60208087028601011115610b8f578788fd5b8793505b84841015610bba57610ba58f82610996565b83526001939093019260209283019201610b93565b50955050505060c089013581811115610bd1578283fd5b610bdd8b828c016109b3565b9250505092959891949750929550565b600060208284031215610bfe578081fd5b5035919050565b6000808354600180821660008114610c245760018114610c3b57610c6a565b60ff198316865260028304607f1686019350610c6a565b600283048786526020808720875b83811015610c625781548a820152908501908201610c49565b505050860193505b509195945050505050565b6001600160a01b0391909116815260200190565b901515815260200190565b6020810160058310610ca257fe5b91905290565b6000602080835283518082850152825b81811015610cd457858101830151858201604001528201610cb8565b81811115610ce55783604083870101525b50601f01601f1916929092016040019392505050565b60208082526010908201526f766f74652073746167652077726f6e6760801b604082015260600190565b90815260200190565b60405181810167ffffffffffffffff81118282101715610d4d57600080fd5b604052919050565b600067ffffffffffffffff821115610d6b578081fd5b506020908102019056fea2646970667358221220d13f0ff6d7a873ad10ce74be1a8ec0f372f0e6a53cf82f4e48851048c2606d1a64736f6c63430006020033";

    public static final String FUNC_BEGINTALLYPHASE = "beginTallyPhase";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_CASTVOTE = "castVote";

    public static final String FUNC_ELECTIONRESULTS = "electionResults";

    public static final String FUNC_ENDREGISTRATIONPHASE = "endRegistrationPhase";

    public static final String FUNC_ENDVOTINGPHASE = "endVotingPhase";

    public static final String FUNC_FINISHSETUP = "finishSetUp";

    public static final String FUNC_ISVOTED = "isVoted";

    public static final String FUNC_NUMOFCANDIDATES = "numOfCandidates";

    public static final String FUNC_NUMOFVOTERS = "numOfVoters";

    public static final String FUNC_REGISTERVOTER = "registerVoter";

    public static final String FUNC_REGISTEREDVOTERS = "registeredVoters";

    public static final String FUNC_REGISTRATIONENDTIME = "registrationEndTime";

    public static final String FUNC_REGISTRATIONSTARTTIME = "registrationStartTime";

    public static final String FUNC_STATE = "state";

    public static final String FUNC_VOTENAME = "voteName";

    public static final String FUNC_VOTESC = "voteSC";

    public static final String FUNC_VOTERADDRESS = "voterAddress";

    public static final String FUNC_VOTINGENDTIME = "votingEndTime";

    public static final String FUNC_VOTINGSTARTTIME = "votingStartTime";

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

    public RemoteFunctionCall<TransactionReceipt> beginTallyPhase() {
        final Function function = new Function(
                FUNC_BEGINTALLYPHASE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> candidates(BigInteger param0) {
        final Function function = new Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> castVote(String _hashedVotes) {
        final Function function = new Function(
                FUNC_CASTVOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_hashedVotes)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> electionResults(String param0) {
        final Function function = new Function(FUNC_ELECTIONRESULTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> endRegistrationPhase() {
        final Function function = new Function(
                FUNC_ENDREGISTRATIONPHASE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> endVotingPhase() {
        final Function function = new Function(
                FUNC_ENDVOTINGPHASE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> finishSetUp(String _voteName, BigInteger _registrationStartTime, BigInteger _registrationEndTime, BigInteger _votingStartTime, BigInteger _votingEndTime, List<String> _voterAddress, List<String> _candidates) {
        final Function function = new Function(
                FUNC_FINISHSETUP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_voteName), 
                new org.web3j.abi.datatypes.generated.Uint256(_registrationStartTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_registrationEndTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_votingStartTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_votingEndTime), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(_voterAddress, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_candidates, org.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isVoted(String param0) {
        final Function function = new Function(FUNC_ISVOTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> numOfCandidates() {
        final Function function = new Function(FUNC_NUMOFCANDIDATES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> numOfVoters() {
        final Function function = new Function(FUNC_NUMOFVOTERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> registerVoter(String publickey) {
        final Function function = new Function(
                FUNC_REGISTERVOTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, publickey)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> registeredVoters(String param0) {
        final Function function = new Function(FUNC_REGISTEREDVOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> registrationEndTime() {
        final Function function = new Function(FUNC_REGISTRATIONENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> registrationStartTime() {
        final Function function = new Function(FUNC_REGISTRATIONSTARTTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> state() {
        final Function function = new Function(FUNC_STATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> voteName() {
        final Function function = new Function(FUNC_VOTENAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> voteSC() {
        final Function function = new Function(FUNC_VOTESC, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> voterAddress(BigInteger param0) {
        final Function function = new Function(FUNC_VOTERADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> votingEndTime() {
        final Function function = new Function(FUNC_VOTINGENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> votingStartTime() {
        final Function function = new Function(FUNC_VOTINGSTARTTIME, 
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
}
