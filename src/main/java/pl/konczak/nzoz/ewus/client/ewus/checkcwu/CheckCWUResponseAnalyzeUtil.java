package pl.konczak.nzoz.ewus.client.ewus.checkcwu;

import pl.konczak.nzoz.ewus.client.ewus.checkcwu.response.CheckCWUResponse;

public final class CheckCWUResponseAnalyzeUtil {

    private CheckCWUResponseAnalyzeUtil() {

    }

    public static boolean isUbezpieczony(CheckCWUResponse checkCWUResponse) {
        if (checkCWUResponse == null) {
            throw new IllegalArgumentException("checkCWUResponse for isUbezpieczony analysis is null!");
        }

        if (!isPacjentStatusOk(checkCWUResponse)) {
            return false;
        }

        if (isPacjentMissing(checkCWUResponse)) {
            return false;
        }

        return isPacjentUbezpieczony(checkCWUResponse);
    }

    private static boolean isPacjentStatusOk(CheckCWUResponse checkCWUResponse) {
        return checkCWUResponse.getPayload().getTextload().getStatusCwuOdp().getStatusCwu() == 1;
    }

    private static boolean isPacjentMissing(CheckCWUResponse checkCWUResponse) {
        return checkCWUResponse.getPayload().getTextload().getStatusCwuOdp().getPacjent() == null;
    }

    private static boolean isPacjentUbezpieczony(CheckCWUResponse checkCWUResponse) {
        return checkCWUResponse.getPayload().getTextload().getStatusCwuOdp().getPacjent().getStatusUbezp().getStatus() == 1;
    }
}
