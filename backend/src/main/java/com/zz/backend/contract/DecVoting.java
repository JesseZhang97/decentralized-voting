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
    public static final String BINARY = "608060405234801561001057600080fd5b506000805460ff19168155610d1590819061002b90396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c8063a300e224116100a2578063b767eb9811610071578063b767eb9814610202578063c19d93fb14610215578063cbff75cb1461022a578063dff7d2c71461023d578063f37251461461024557610116565b8063a300e224146101d7578063a9ff6df1146101df578063b21a64da146101f2578063b43d5f15146101fa57610116565b806338db6dd3116100e957806338db6dd3146101745780635d55f2de1461019457806386f5114e146101b457806391b48f31146101bc5780639af5c09d146101cf57610116565b80631ea736e01461011b5780632019a6081461013957806332aaaae1146101415780633477ee2e14610154575b600080fd5b610123610258565b6040516101309190610c8f565b60405180910390f35b61012361025e565b61012361014f366004610a46565b610264565b610167610162366004610a46565b610276565b6040516101309190610c12565b610187610182366004610a24565b61031c565b6040516101309190610bf3565b6101a76101a2366004610a46565b6103c4565b6040516101309190610bdf565b6101676103eb565b6101236101ca366004610a5e565b610445565b61012361052b565b610187610531565b6101876101ed366004610a24565b610582565b610187610597565b6101876105f1565b610187610210366004610ad3565b610635565b61021d610713565b6040516101309190610bfe565b610187610238366004610a24565b61071c565b610123610731565b610187610253366004610a98565b610737565b60055481565b60045481565b600c6020526000908152604090205481565b6007818154811061028357fe5b600091825260209182902001805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152935090918301828280156103145780601f106102e957610100808354040283529160200191610314565b820191906000526020600020905b8154815290600101906020018083116102f757829003601f168201915b505050505081565b600060018060005460ff16600481111561033257fe5b146103585760405162461bcd60e51b815260040161034f90610c65565b60405180910390fd5b60025442101561036b57600091506103be565b6001600160a01b03831660009081526009602052604090205460ff161561039557600091506103be565b6001600160a01b0383166000908152600960205260409020805460ff1916600190811790915591505b50919050565b600681815481106103d157fe5b6000918252602090912001546001600160a01b0316905081565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156103145780601f106102e957610100808354040283529160200191610314565b600060028060005460ff16600481111561045b57fe5b146104785760405162461bcd60e51b815260040161034f90610c65565b6001600160a01b03831660009081526009602052604090205460ff166104a15760019150610524565b6001600160a01b0383166000908152600a602052604090205460ff16156104cb5760029150610524565b600b805460018181019092557f0175b7a638427703f0dbe7bb9bbf987a2551717b34e79f33b5b1008d1fa01db9018590556001600160a01b0384166000908152600a60205260409020805460ff19169091179055600391505b5092915050565b60025481565b600060028060005460ff16600481111561054757fe5b146105645760405162461bcd60e51b815260040161034f90610c65565b600080546003919060ff19166001835b0217905550600191505b5090565b60096020526000908152604090205460ff1681565b600060018060005460ff1660048111156105ad57fe5b146105ca5760405162461bcd60e51b815260040161034f90610c65565b6003544210156105dd576000915061057e565b600080546002919060ff1916600183610574565b600060038060005460ff16600481111561060757fe5b146106245760405162461bcd60e51b815260040161034f90610c65565b506000805460ff1916600417905590565b6000808060005460ff16600481111561064a57fe5b146106675760405162461bcd60e51b815260040161034f90610c65565b428810156106785760009150610707565b868811156106895760009150610707565b8587118061069657508486115b156106a45760009150610707565b88516106b79060019060208c0190610757565b50600288905560038790556004869055600585905583516106df9060069060208701906107d1565b5082516106f3906007906020860190610832565b506000805460ff1916600190811790915591505b50979650505050505050565b60005460ff1681565b600a6020526000908152604090205460ff1681565b60035481565b805160208183018101805160088252928201919093012091525460ff1681565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061079857805160ff19168380011785556107c5565b828001600101855582156107c5579182015b828111156107c55782518255916020019190600101906107aa565b5061057e92915061088b565b828054828255906000526020600020908101928215610826579160200282015b8281111561082657825182546001600160a01b0319166001600160a01b039091161782556020909201916001909101906107f1565b5061057e9291506108a8565b82805482825590600052602060002090810192821561087f579160200282015b8281111561087f578251805161086f918491602090910190610757565b5091602001919060010190610852565b5061057e9291506108cc565b6108a591905b8082111561057e5760008155600101610891565b90565b6108a591905b8082111561057e5780546001600160a01b03191681556001016108ae565b6108a591905b8082111561057e5760006108e682826108ef565b506001016108d2565b50805460018160011615610100020316600290046000825580601f106109155750610933565b601f016020900490600052602060002090810190610933919061088b565b50565b80356001600160a01b038116811461094d57600080fd5b92915050565b600082601f830112610963578081fd5b813561097661097182610cbf565b610c98565b818152915060208083019084810160005b848110156109b05761099e888484358a01016109bb565b84529282019290820190600101610987565b505050505092915050565b600082601f8301126109cb578081fd5b813567ffffffffffffffff8111156109e1578182fd5b6109f4601f8201601f1916602001610c98565b9150808252836020828501011115610a0b57600080fd5b8060208401602084013760009082016020015292915050565b600060208284031215610a35578081fd5b610a3f8383610936565b9392505050565b600060208284031215610a57578081fd5b5035919050565b60008060408385031215610a70578081fd5b8235915060208301356001600160a01b0381168114610a8d578182fd5b809150509250929050565b600060208284031215610aa9578081fd5b813567ffffffffffffffff811115610abf578182fd5b610acb848285016109bb565b949350505050565b600080600080600080600060e0888a031215610aed578283fd5b67ffffffffffffffff8089351115610b03578384fd5b610b108a8a358b016109bb565b97506020890135965060408901359550606089013594506080890135935060a089013581811115610b3f578384fd5b808a018b601f820112610b50578485fd5b80359150610b6061097183610cbf565b80838252602082019150602083018e60208087028601011115610b81578788fd5b8793505b84841015610bac57610b978f82610936565b83526001939093019260209283019201610b85565b50955050505060c089013581811115610bc3578283fd5b610bcf8b828c01610953565b9250505092959891949750929550565b6001600160a01b0391909116815260200190565b901515815260200190565b6020810160058310610c0c57fe5b91905290565b6000602080835283518082850152825b81811015610c3e57858101830151858201604001528201610c22565b81811115610c4f5783604083870101525b50601f01601f1916929092016040019392505050565b60208082526010908201526f766f74652073746167652077726f6e6760801b604082015260600190565b90815260200190565b60405181810167ffffffffffffffff81118282101715610cb757600080fd5b604052919050565b600067ffffffffffffffff821115610cd5578081fd5b506020908102019056fea2646970667358221220f060355210ee602a288e344ac451593872be4240353cda1f08bdc250be4237f664736f6c63430006020033";

    public static final String FUNC_BEGINTALLYPHASE = "beginTallyPhase";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_CANDIDATESMAP = "candidatesMap";

    public static final String FUNC_CASTVOTE = "castVote";

    public static final String FUNC_ELECTIONRESULTS = "electionResults";

    public static final String FUNC_ENDREGISTRATIONPHASE = "endRegistrationPhase";

    public static final String FUNC_ENDVOTINGPHASE = "endVotingPhase";

    public static final String FUNC_FINISHSETUP = "finishSetUp";

    public static final String FUNC_ISVOTED = "isVoted";

    public static final String FUNC_REGISTERVOTER = "registerVoter";

    public static final String FUNC_REGISTEREDVOTERS = "registeredVoters";

    public static final String FUNC_REGISTRATIONENDTIME = "registrationEndTime";

    public static final String FUNC_REGISTRATIONSTARTTIME = "registrationStartTime";

    public static final String FUNC_STATE = "state";

    public static final String FUNC_VOTENAME = "voteName";

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

    public RemoteFunctionCall<Boolean> candidatesMap(String param0) {
        final Function function = new Function(FUNC_CANDIDATESMAP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> castVote(byte[] _hashedVotes, String _voterAddress) {
        final Function function = new Function(
                FUNC_CASTVOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_hashedVotes), 
                new org.web3j.abi.datatypes.Address(160, _voterAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> electionResults(byte[] param0) {
        final Function function = new Function(FUNC_ELECTIONRESULTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
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
