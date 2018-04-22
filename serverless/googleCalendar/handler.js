import { google } from 'googleapis';

// overwrite env var path
process.env['GOOGLE_APPLICATION_CREDENTIALS'] = process.cwd() + '/creds.json';

const calendar = google.calendar('v3');
const scopes = ['https://www.googleapis.com/auth/calendar.readonly'];
const calendarId = process.env.CALENDAR_ID;


const fetchEventList = async () => {
    // This method looks for the GCLOUD_PROJECT and GOOGLE_APPLICATION_CREDENTIAL environment variables
    const auth = await google.auth.getClient({
        scopes,
    });

    // obtain the current project Id
    const project = await google.auth.getDefaultProjectId();

    // Fetch the list of GCE zones within a project.
    try {
        return await calendar.events.list({
            project,
            auth,
            calendarId,
            timeMin: '2018-04-20T14:37:45-06:00',
            showDeleted: false,
            singleEvents: true,
            // maxResults: 25,
            orderBy: 'startTime',
        });
    } catch (e) {
        throw new Error(e);
    }
};

const main = async () => {
    try {
        const { data: { items } } = await fetchEventList();
        return items;
    } catch (e) {
        throw new Error(e);
    }
}

export const events = async (event, context, callback) =>
    callback(null, {
        headers: {
            "Access-Control-Allow-Origin" : "*" // Required for CORS support to work
        },
        statusCode: 200,
        body: JSON.stringify(await main()),
    });
