$("#registrer").click(()=> {
    const inputData = hentInputFelter()
    if(validering(inputData)) {
        if(validerGyldigPostnr(inputData.postnr)) {
            lagreData(inputData)
        }
        else {
            $("#postnrFeil").html("Du må skrive inn et gyldig postnr.")
        }
    }
})

function lagreData(data) {
    $.post("/registrerPakke", data, () => {

    })
}

function hentInputFelter() {
    const data = {
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        adresse : $("#adresse").val(),
        postnr : $("#postnr").val(),
        telefonnr : $("#telefonnr").val(),
        epost : $("#epost").val(),
        volum : $("#volum").val(),
        vekt : $("#vekt").val()
    }
    return data
}

function validerGyldigPostnr(postnr) {
    let gyldig = false
    $.ajax({
        url: "/sjekkPostnr?postnr="+postnr,
        type: "GET",
        async: false,
        success: status => {
            gyldig = status;
        }
    })
        .fail(jqXHR => {
            const json = $.parseJSON(jqXHR.responseText)
            $("feilmeldingFelt").html(json.message)
        })

    if(gyldig) {
        //Håndtering av validert gyldig postnr
        return true
    }
    else {
        //Håndtering av validert ugyldig postnr
        return false
    }

}

function validering(inputData) {
    const validerNavn = data => {
        const regex = /^[a-zæøåA-ZÆØÅ' .\-]{2,50}$/;
        return regex.test(data)
    }
    const validerPostNr = data => {
        const regex = /^[\d]{4}$/;
        return regex.test(data);
    }

    const fornavnOK = validerNavn(inputData.fornavn)
    const etternavnOK = validerNavn(inputData.etternavn)
    const postnrOK = validerPostNr(inputData.postnr)

    if(!fornavnOK) {
        $("#fornavnFeil").html("Navnet må bestå av 2 til 50 bokstaver")
    }
    if(!etternavnOK) {
        $("#etternavnFeil").html("Navnet må bestå av 2 til 50 bokstaver")
    }
    if(!postnrOK) {
        $("#postnrFeil").html("Du må skrive inn et gyldig postnr")
    }
    return fornavnOK && etternavnOK && postnrOK
}
