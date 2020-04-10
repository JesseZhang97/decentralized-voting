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
    public static final String BINARY = "60806040526103e760015534801561001657600080fd5b506000805460ff19168155610d9c90819061003190396000f3fe608060405234801561001057600080fd5b506004361061012c5760003560e01c80639cc2d993116100ad578063b767eb9811610071578063b767eb9814610228578063c19d93fb1461023b578063cbff75cb14610250578063d299346b14610263578063dff7d2c71461026b5761012c565b80639cc2d993146101ea578063a300e224146101fd578063a9ff6df114610205578063b21a64da14610218578063b43d5f15146102205761012c565b806364f62cc0116100f457806364f62cc0146101b757806381929bac146101bf57806386f5114e146101d257806398f0b34c146101da5780639af5c09d146101e25761012c565b80631ea736e0146101315780632019a6081461014f5780633477ee2e1461015757806338db6dd3146101775780635d55f2de14610197575b600080fd5b610139610273565b6040516101469190610d16565b60405180910390f35b610139610279565b61016a610165366004610bde565b61027f565b6040516101469190610c99565b61018a610185366004610a75565b610325565b6040516101469190610c7a565b6101aa6101a5366004610bde565b6103ba565b6040516101469190610c66565b6101396103e1565b6101396101cd366004610a97565b6103e8565b61016a610405565b61013961045d565b610139610463565b61018a6101f8366004610a97565b610469565b61018a61054f565b61018a610213366004610a75565b6105a0565b61018a6105b5565b61018a6105fc565b61018a610236366004610ad2565b6106e2565b610243610781565b6040516101469190610c85565b61018a61025e366004610a75565b61078a565b61013961079f565b6101396107a5565b60065481565b60055481565b6008818154811061028c57fe5b600091825260209182902001805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529350909183018282801561031d5780601f106102f25761010080835404028352916020019161031d565b820191906000526020600020905b81548152906001019060200180831161030057829003601f168201915b505050505081565b600060018060005460ff16600481111561033b57fe5b146103615760405162461bcd60e51b815260040161035890610cec565b60405180910390fd5b6001600160a01b03831660009081526009602052604090205460ff161561038b57600091506103b4565b6001600160a01b0383166000908152600960205260409020805460ff1916600190811790915591505b50919050565b600781815481106103c757fe5b6000918252602090912001546001600160a01b0316905081565b6008545b90565b8051602081830181018051600c8252928201919093012091525481565b6002805460408051602060018416156101000260001901909316849004601f8101849004840282018401909252818152929183018282801561031d5780601f106102f25761010080835404028352916020019161031d565b60075490565b60035481565b600060028060005460ff16600481111561047f57fe5b1461049c5760405162461bcd60e51b815260040161035890610cec565b3360009081526009602052604090205460ff166104c05760018055600091506103b4565b336000908152600b602052604090205460ff16156104e6576002600155600091506103b4565b600a80546001810182556000919091528351610529917fc65a7bb8d6351c1cf70c95a316cc6a92839c986682d98bc35f958f4883f9d2a8019060208601906107ab565b50336000908152600b60205260409020805460ff19166001908117909155915050919050565b600060028060005460ff16600481111561056557fe5b146105825760405162461bcd60e51b815260040161035890610cec565b600080546003919060ff19166001835b0217905550600191505b5090565b60096020526000908152604090205460ff1681565b600060018060005460ff1660048111156105cb57fe5b146105e85760405162461bcd60e51b815260040161035890610cec565b600080546002919060ff1916600183610592565b600060038060005460ff16600481111561061257fe5b1461062f5760405162461bcd60e51b815260040161035890610cec565b60005b60085481101561067c576000600c6008838154811061064d57fe5b906000526020600020016040516106649190610bf6565b90815260405190819003602001902055600101610632565b5060005b600a548110156106d0576001600c600a838154811061069b57fe5b906000526020600020016040516106b29190610bf6565b90815260405190819003602001902080549091019055600101610680565b50506000805460ff1916600417905590565b6000808060005460ff1660048111156106f757fe5b146107145760405162461bcd60e51b815260040161035890610cec565b88516107279060029060208c01906107ab565b506003889055600487905560058690556006859055835161074f906007906020870190610825565b508251610763906008906020860190610886565b50506000805460ff1916600190811790915598975050505050505050565b60005460ff1681565b600b6020526000908152604090205460ff1681565b60015481565b60045481565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106107ec57805160ff1916838001178555610819565b82800160010185558215610819579182015b828111156108195782518255916020019190600101906107fe565b5061059c9291506108df565b82805482825590600052602060002090810192821561087a579160200282015b8281111561087a57825182546001600160a01b0319166001600160a01b03909116178255602090920191600190910190610845565b5061059c9291506108f9565b8280548282559060005260206000209081019282156108d3579160200282015b828111156108d357825180516108c39184916020909101906107ab565b50916020019190600101906108a6565b5061059c92915061091d565b6103e591905b8082111561059c57600081556001016108e5565b6103e591905b8082111561059c5780546001600160a01b03191681556001016108ff565b6103e591905b8082111561059c5760006109378282610940565b50600101610923565b50805460018160011615610100020316600290046000825580601f106109665750610984565b601f01602090049060005260206000209081019061098491906108df565b50565b80356001600160a01b038116811461099e57600080fd5b92915050565b600082601f8301126109b4578081fd5b81356109c76109c282610d46565b610d1f565b818152915060208083019084810160005b84811015610a01576109ef888484358a0101610a0c565b845292820192908201906001016109d8565b505050505092915050565b600082601f830112610a1c578081fd5b813567ffffffffffffffff811115610a32578182fd5b610a45601f8201601f1916602001610d1f565b9150808252836020828501011115610a5c57600080fd5b8060208401602084013760009082016020015292915050565b600060208284031215610a86578081fd5b610a908383610987565b9392505050565b600060208284031215610aa8578081fd5b813567ffffffffffffffff811115610abe578182fd5b610aca84828501610a0c565b949350505050565b600080600080600080600060e0888a031215610aec578283fd5b67ffffffffffffffff8089351115610b02578384fd5b610b0f8a8a358b01610a0c565b97506020890135965060408901359550606089013594506080890135935060a089013581811115610b3e578384fd5b808a018b601f820112610b4f578485fd5b80359150610b5f6109c283610d46565b80838252602082019150602083018e60208087028601011115610b80578788fd5b8793505b84841015610bab57610b968f82610987565b83526001939093019260209283019201610b84565b50955050505060c089013581811115610bc2578283fd5b610bce8b828c016109a4565b9250505092959891949750929550565b600060208284031215610bef578081fd5b5035919050565b6000808354600180821660008114610c155760018114610c2c57610c5b565b60ff198316865260028304607f1686019350610c5b565b600283048786526020808720875b83811015610c535781548a820152908501908201610c3a565b505050860193505b509195945050505050565b6001600160a01b0391909116815260200190565b901515815260200190565b6020810160058310610c9357fe5b91905290565b6000602080835283518082850152825b81811015610cc557858101830151858201604001528201610ca9565b81811115610cd65783604083870101525b50601f01601f1916929092016040019392505050565b60208082526010908201526f766f74652073746167652077726f6e6760801b604082015260600190565b90815260200190565b60405181810167ffffffffffffffff81118282101715610d3e57600080fd5b604052919050565b600067ffffffffffffffff821115610d5c578081fd5b506020908102019056fea264697066735822122076379333d481e93987189ce76ead13008b0cf7f8a3f9cfdaaaae8cd285e743df64736f6c63430006020033";

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
