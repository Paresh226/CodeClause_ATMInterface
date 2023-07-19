<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>ATM Interface</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
	function validateAmount(input) {
		var amount = input.value;

		if (amount === '0') {
			document.getElementById('amountError').textContent = 'Amount cannot be zero';
			input.setCustomValidity('Amount cannot be zero');
		} else {
			document.getElementById('amountError').textContent = '';
			input.setCustomValidity('');
		}
	}
	
	 function restrictNonNumericInput(input) {
	        input.value = input.value.replace(/\D/g, '');
	    }
</script>
 
</head>
<body>
	<div class="container mt-5">
		<h1>Welcome to the ATM Interface</h1>
		<ul class="nav nav-tabs mt-5">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#withdraw">Withdraw Money</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#deposit">Deposit Money</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#balance">Check Account Balance</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#forgotpin">Forgot PIN</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#statement">Print Account Statement</a></li>
		</ul>

		<div class="tab-content mt-5">
			<div class="tab-pane fade show active" id="withdraw">
				<h4>Withdraw Money</h4>
				<form action="WithdrowMoney" method="post">
				<div class="form-group">
						  <input type="hidden" id="TransactionType" name="TransactionType" value="Withdrow">
					</div>
					<div class="form-group">
						<label for="withdrawAccountNumber">Card Number</label> <input
							type="number" class="form-control" id="cardno" name="cardno" placeholder="Please enter Card Number" 
							maxlength="6" required>
					</div>
					<div class="form-group">
						<label for="withdrawPIN">PIN</label> <input type="password"
							class="form-control" id="pin" name="pin" pattern="[0-9]{4}"
							placeholder="Please enter a 4-digit PIN" required maxlength="4"
							oninput="restrictNonNumericInput(this);">
					</div>

					<div class="form-group">
						<label for="withdrawAmount">Amount</label> <input type="text"
							class="form-control" id="amount" name="amount" required placeholder="Enter Amount" 
							oninput="validateAmount(this)"> <span id="amountError"
							style="color: red;"></span>
					</div>
					<button type="submit" class="btn btn-primary">Withdraw</button>
				</form>
			</div>

			<div class="tab-pane fade" id="deposit">
				<h4>Deposit Money</h4>
				<form action="DepositMoney" method="post">
				 
				<div class="form-group">
						  <input type="hidden" id="TransactionType" name="TransactionType" value="Deposited">
					</div>
				
					<div class="form-group">
						<label for="withdrawAccountNumber">Card Number</label> <input
							type="number" class="form-control" id="cardno" name="cardno" placeholder="Please enter Card Number" 
							maxlength="6" required>
					</div>
					<div class="form-group">
						<label for="withdrawPIN">PIN</label> <input type="password"
							class="form-control" id="pin" name="pin" pattern="[0-9]{4}"
							placeholder="Please enter a 4-digit PIN" required maxlength="4"
							oninput="restrictNonNumericInput(this);">
					</div>
					<div class="form-group">
						<label for="withdrawAmount">Amount</label> <input type="text"
							class="form-control" id="amount" name="amount" required placeholder="Enter Amount" 
							oninput="validateAmount(this)"> <span id="amountError"
							style="color: red;"></span>
					</div>
					<button type="submit" class="btn btn-primary">Deposit</button>
				</form>

			</div>

			<div class="tab-pane fade" id="balance">
				<h4>Check Account Balance</h4>
				<form action="BalanceCheck" method="post">
				<div class="form-group">
						<label for="withdrawAccountNumber">Card Number</label> <input
							type="number" class="form-control" id="cardno" name="cardno" placeholder="Please enter Card Number" 
							maxlength="6" required>
					</div>
					<div class="form-group">
						<label for="withdrawPIN">PIN</label> <input type="password"
							class="form-control" id="pin" name="pin" pattern="[0-9]{4}"
							placeholder="Please enter a 4-digit PIN" required maxlength="4"
							oninput="restrictNonNumericInput(this);">
					</div>
					<button type="submit" class="btn btn-primary">Check
						Balance</button>
				</form>
			</div>

			<div class="tab-pane fade" id="forgotpin">
				<h4>Forgot PIN</h4>
				<form action="ForgotPin" method="post">
					<div class="form-group">
						<label for="withdrawAccountNumber">Card Number</label> <input
							type="number" class="form-control" id="cardno" name="cardno" placeholder="Please enter Card Number" 
							maxlength="6" required>
					</div>
					<div class="form-group">
						<label for="accountno">Account Number</label> <input type="number"
							class="form-control" id="accountno" name="accountno" maxlength="" placeholder="Enter Bank Account Number" 
							required>
					</div>
					<button type="submit" class="btn btn-primary">Retrieve PIN</button>
				</form>
			</div>

			<div class="tab-pane fade" id="statement">
				<h4>Print Account Statement</h4>
				<form action="Statement" method="post">
				<div class="form-group">
						<label for="withdrawAccountNumber">Card Number</label> <input
							type="number" class="form-control" id="cardno" name="cardno" placeholder="Please enter Card Number" 
							maxlength="6" required>
					</div>
					<div class="form-group">
						<label for="withdrawPIN">PIN</label> <input type="password"
							class="form-control" id="pin" name="pin" pattern="[0-9]{4}"
							placeholder="Please enter a 4-digit PIN" required maxlength="4"
							oninput="restrictNonNumericInput(this);">
					</div>
					<button type="submit" class="btn btn-primary">View Account Statement</button>
				</form>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
